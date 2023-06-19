package com.project.literature_club.controller;

import com.project.literature_club.dtos.article.ArticleResponseDTO;
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
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final ArticleService articleService;
    private final UserService userService;
    private final JwtService jwtService;
    private final DTOMapper dtoMapper;

    @GetMapping
    public List<ArticleResponseDTO> likedArticles(@RequestHeader("Authorization") String token){
       return articleService.getLikedArticles(token);
    }

    @PostMapping("/{id}")
    public boolean like(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        articleService.like(id,token);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean disLike(@PathVariable Long id, @RequestHeader("Authorization") String token){
       articleService.dislike(id,token);
        return true;
    }
}
