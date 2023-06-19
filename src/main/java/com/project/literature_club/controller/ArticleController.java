package com.project.literature_club.controller;


import com.project.literature_club.dtos.article.ArticleResponseDTO;
import com.project.literature_club.dtos.article.CreateArticleRequestDTO;
import com.project.literature_club.entity.Article;
import com.project.literature_club.entity.User;
import com.project.literature_club.service.ArticleService;
import com.project.literature_club.service.JwtService;
import com.project.literature_club.service.UserService;
import com.project.literature_club.util.DTOMapper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;



    @PostMapping
    public ArticleResponseDTO createArticle(@RequestBody CreateArticleRequestDTO article, @RequestHeader("Authorization") String token) {
        return articleService.createArticle(article,token);
    }

    @GetMapping("/{id}")
    public ArticleResponseDTO getArticleById(@PathVariable long id, @RequestHeader("Authorization") String token) {

        return articleService.getArticleById(id,token);
    }

    @GetMapping("/myArticles")
    public List<ArticleResponseDTO> getMyArticles(@RequestHeader("Authorization") String token) {
        return articleService.getUsersArticles(token);
    }

    @GetMapping("author/{id}")
    public List<ArticleResponseDTO> getArticlesByUserId(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return articleService.getArticlesByUsersId(id,token);
    }

    @GetMapping
    public List<ArticleResponseDTO> getAllArticles(@RequestHeader("Authorization") String token) {
        return articleService.getAll(token);
    }




}
