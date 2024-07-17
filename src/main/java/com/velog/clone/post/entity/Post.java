package com.velog.clone.post.entity;

import com.velog.clone.post.dto.PostDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String author;

    private LocalDate date;

    private int likes;

    private int comments;

    @PrePersist
    public void prePersist() {
        if (this.date == null) {
            this.date = LocalDate.now();
        }
    }

    public static Post toEntity(PostDto.Create dto) {
        return Post.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .author(dto.getAuthor())
                .date(dto.getDate())
                .likes(dto.getLikes())
                .comments(dto.getComments())
                .build();
    }

    public void incrementComments() {
        this.comments++;
    }

    public void decrementComments() {
        this.comments--;
    }
}
