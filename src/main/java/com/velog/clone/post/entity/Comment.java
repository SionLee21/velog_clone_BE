package com.velog.clone.post.entity;

import jakarta.persistence.Entity;
import lombok.*;

import com.velog.clone.post.dto.PostDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    private String content;

    // getters and setters
}
