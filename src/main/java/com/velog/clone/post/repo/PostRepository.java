package com.velog.clone.post.repo;

import com.velog.clone.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<Post> findTopByIdLessThanOrderByIdDesc(Long id);
    Optional<Post> findTopByIdGreaterThanOrderByIdAsc(Long id);
}
