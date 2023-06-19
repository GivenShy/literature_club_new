package com.project.literature_club.dtos.article;

import lombok.Getter;
import lombok.Setter;

public class CreateArticleRequestDTO {

    @Getter
    @Setter
    private String title;

//    @Getter
//    @Setter
//    private Long user_id;

    @Getter
    @Setter
    private String text;


}
