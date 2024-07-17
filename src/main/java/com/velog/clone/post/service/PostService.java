package com.velog.clone.post.service;

import com.velog.clone.post.dto.PostDto;
import com.velog.clone.post.entity.Comment;
import com.velog.clone.post.entity.Post;
import com.velog.clone.post.repo.CommentRepository;
import com.velog.clone.post.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<PostDto.Read> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostDto.Read::new)
                .collect(Collectors.toList());
    }

    public PostDto.Read getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostDto.Read::new)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public PostDto.Read createPost(PostDto.Create postDto) {
        Post post = Post.toEntity(postDto);
        Post savedPost = postRepository.save(post);
        return new PostDto.Read(savedPost);
    }

    public Optional<Post> getPreviousPost(Long postId) {
        return postRepository.findTopByIdLessThanOrderByIdDesc(postId);
    }

    public Optional<Post> getNextPost(Long postId) {
        return postRepository.findTopByIdGreaterThanOrderByIdAsc(postId);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment addComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPostId(postId);
        Comment savedComment = commentRepository.save(comment);
        post.incrementComments();
        postRepository.save(post);
        return savedComment;
    }

    public List<PostDto.Read> getPostsByDateRange(LocalDate startDate, LocalDate endDate) {
        return postRepository.findByDateBetween(startDate, endDate).stream()
                .map(PostDto.Read::new)
                .collect(Collectors.toList());
    }
    public PostDto.Read likePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setLikes(post.getLikes() + 1);
        Post updatedPost = postRepository.save(post);

        return new PostDto.Read(updatedPost);
    }
    public List<PostDto.Read> getTrendingPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "likes")).stream()
                .map(PostDto.Read::new)
                .collect(Collectors.toList());
    }

    public List<PostDto.Read> getLatestPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "date")).stream()
                .map(PostDto.Read::new)
                .collect(Collectors.toList());
    }
    public PostDto.Read updatePost(Long postId, PostDto.Create postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        Post updatedPost = postRepository.save(post);

        return new PostDto.Read(updatedPost);
    }
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
    }
}
