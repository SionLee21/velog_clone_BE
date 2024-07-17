package com.velog.clone.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.velog.clone.post.entity.Post;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class PostDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Create {

            private String title;

            private String description;

            private String author;

            private LocalDate date;

            private int likes;

            private int comments;
    }
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read {
        private Long id;
        private String title;
        private String description;
        private String author;
        private LocalDate date;
        private int likes;
        private int comments;

        public Read(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.description = post.getDescription();
            this.author = post.getAuthor();
            this.date = post.getDate();
            this.likes = post.getLikes();
            this.comments = post.getComments();
        }
    }

}
