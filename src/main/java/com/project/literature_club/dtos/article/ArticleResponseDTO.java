package com.project.literature_club.dtos.article;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ArticleResponseDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private String authorName;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private Long likeCount;

    @Getter
    @Setter
    private boolean isLiked;
}
