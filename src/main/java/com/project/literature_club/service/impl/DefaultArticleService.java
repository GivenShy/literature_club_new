package com.project.literature_club.service.impl;


import com.project.literature_club.dtos.article.ArticleResponseDTO;
import com.project.literature_club.dtos.article.CreateArticleRequestDTO;
import com.project.literature_club.entity.Article;
import com.project.literature_club.entity.User;
import com.project.literature_club.repositories.ArticleRepository;
import com.project.literature_club.service.ArticleService;
import com.project.literature_club.service.JwtService;
import com.project.literature_club.service.UserService;
import com.project.literature_club.util.DTOMapper;
import org.springframework.stereotype.Service;
import com.project.literature_club.exceptions.ArticleNotFoundException;
import com.project.literature_club.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultArticleService implements ArticleService {
    private final ArticleRepository repository;
    private final UserService userService;

    private final DTOMapper dtoMapper;

    public DefaultArticleService(ArticleRepository repository, UserService userService, JwtService jwtService, DTOMapper dtoMapper){
        this.repository = repository;
        this.userService = userService;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ArticleResponseDTO> getLikedArticles(String token){
        User user = userService.getByToken(token);
        return dtoMapper.articlesToDTO(user.getLikedArticles(),user);
    }

    @Override
    public List<ArticleResponseDTO> getArticlesByUsersId(Long id, String token){
        User user = userService.getByToken(token);
        User otherUser = userService.get(id);
        return dtoMapper.articlesToDTO(otherUser.getMyArticles(),user);
    }

    @Override
    public List<ArticleResponseDTO> getAll(String token){
        User user = userService.getByToken(token);
        return dtoMapper.articlesToDTO(repository.findAll(),user);

    }

    @Override
    public List<ArticleResponseDTO> getUsersArticles(String token){
        User user = userService.getByToken(token);
        return dtoMapper.articlesToDTO(user.getMyArticles(),user);
    }
    @Override
    public ArticleResponseDTO getArticleById(long id, String token){
        User user = userService.getByToken(token);
        Article article = repository.findById(id).orElseThrow(ArticleNotFoundException::new);
        return dtoMapper.createArticleDTOFromArticle(article,user);
    }

    @Override
    public ArticleResponseDTO createArticle(CreateArticleRequestDTO createArticleRequestDTO, String token){
        User user = userService.getByToken(token);
        Article article = dtoMapper.createArticleFromDTO(createArticleRequestDTO, user);
        save(article);
        return dtoMapper.createArticleDTOFromArticle(article,user);
    }
    @Override
    public Article get(Long id) {
        Optional<Article> user = repository.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void like(long id,String token){
        User user = userService.getByToken(token);
        Article article = get(id);
        user.getLikedArticles().add(article);
    }

    @Override
    public void dislike(long id, String token){
        User user = userService.getByToken(token);
        Article article = get(id);
        user.getLikedArticles().remove(article);
    }
    @Override
    public Article save(Article user) {
        repository.save(user);
        return user;
    }


    @Override
    public void delete(Long id) {
        Optional<Article> author = repository.findById(id);
        repository.delete(author.orElseThrow(ArticleNotFoundException::new));
    }

    @Override
    public List<Article> getArticlesByUserId(Long id) {
        return repository.findByAuthor_Id(id);
    }

    @Override
    public Article update(Article article) {
         return repository.save(article);
    }

    @Override
    public List<Article> getAll() {
        return repository.findAll();
    }

    public List<Article> search(String str){
        return repository.search(str.toLowerCase());
    }



}
