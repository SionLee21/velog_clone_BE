package com.velog.clone.post.controller;

import com.velog.clone.post.dto.PostDto;
import com.velog.clone.post.entity.Comment;
import com.velog.clone.post.entity.Post;
import com.velog.clone.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDto.Read> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto.Read getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto.Read createPost(@RequestBody PostDto.Create postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping("/{postId}/previous")
    public Optional<Post> getPreviousPost(@PathVariable Long postId) {
        return postService.getPreviousPost(postId);
    }

    @GetMapping("/{postId}/next")
    public Optional<Post> getNextPost(@PathVariable Long postId) {
        return postService.getNextPost(postId);
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> getComments(@PathVariable Long postId) {
        return postService.getComments(postId);
    }

    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return postService.addComment(postId, comment);
    }

    @GetMapping("/date-range")
    public List<PostDto.Read> getPostsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return postService.getPostsByDateRange(startDate, endDate);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<PostDto.Read> likePost(@PathVariable Long postId) {
        PostDto.Read updatedPost = postService.likePost(postId);
        return ResponseEntity.ok(updatedPost);
    }
    @GetMapping("/trending")
    public List<PostDto.Read> getTrendingPosts() {
        return postService.getTrendingPosts();
    }

    @GetMapping("/latest")
    public List<PostDto.Read> getLatestPosts() {
        return postService.getLatestPosts();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto.Read> updatePost(@PathVariable Long postId, @RequestBody PostDto.Create postDto) {
        PostDto.Read updatedPost = postService.updatePost(postId, postDto);
        return ResponseEntity.ok(updatedPost);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
