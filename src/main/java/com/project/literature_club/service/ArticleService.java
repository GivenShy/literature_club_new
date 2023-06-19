package com.project.literature_club.service;



import com.project.literature_club.dtos.article.ArticleResponseDTO;
import com.project.literature_club.dtos.article.CreateArticleRequestDTO;
import com.project.literature_club.entity.Article;

import java.util.List;

public interface ArticleService {


    List<ArticleResponseDTO> getLikedArticles(String token);

    List<ArticleResponseDTO> getArticlesByUsersId(Long id, String token);


    List<ArticleResponseDTO> getAll(String token);

    List<ArticleResponseDTO> getUsersArticles(String token);

    ArticleResponseDTO getArticleById(long id, String token);

    ArticleResponseDTO createArticle(CreateArticleRequestDTO createArticleRequestDTO, String username);

    Article get(Long id);

    void like(long id, String token);

    void dislike(long id, String token);

    Article save(Article article);

    void delete(Long id);

    List<Article> getArticlesByUserId(Long id);

    Article update(Article article);

    List<Article> getAll();

    List<Article> search(String str);

}
