package com.project.literature_club.dtos.search;

import com.project.literature_club.dtos.article.ArticleResponseDTO;
import com.project.literature_club.dtos.user.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SearchResponseDTO {

    @Getter
    @Setter
    private List<UserResponseDTO> userList;

    @Getter
    @Setter
    private List<ArticleResponseDTO> articleList;
}
