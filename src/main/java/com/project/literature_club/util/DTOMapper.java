package com.project.literature_club.util;

import com.project.literature_club.dtos.article.ArticleResponseDTO;
import com.project.literature_club.dtos.article.CreateArticleRequestDTO;
import com.project.literature_club.dtos.user.UserResponseDTO;
import com.project.literature_club.entity.Article;
import com.project.literature_club.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class DTOMapper {
    public List<UserResponseDTO> convertUserToDTOList(List<User> users, User user){
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>(users.size());
        for(User u:users){
            userResponseDTOList.add(UserResponseDTO.builder()
                    .id(u.getId())
                    .firstName(u.getFirstName())
                    .lastName(u.getLastName())
                    .email(u.getEmail())
                    .description(u.getDescription())
                    .posts(u.getMyArticles().size())
                    .dateOfBirth(u.getDateOfBirth())
                    .mainGenre(u.getMainGenre())
                    .isLiked(user.getFollowedUsers().contains(user))
                    .build());
        }
        return userResponseDTOList;
    }

    public UserResponseDTO convertUserToDTO(User user,User u){
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .description(user.getDescription())
                .posts(user.getMyArticles().size())
                .dateOfBirth(user.getDateOfBirth())
                .mainGenre(user.getMainGenre())
                .isLiked(user.getFollowedUsers().contains(u))
                .build();
    }
    public Article createArticleFromDTO(CreateArticleRequestDTO createArticleRequestDTO, User user){
        Article article = new Article();
        article.setTitle(createArticleRequestDTO.getTitle());
        article.setText(createArticleRequestDTO.getText());
        article.setAuthor(user);
        article.setLikeCount(0L);
        return article;
    }

    public ArticleResponseDTO createArticleDTOFromArticle(Article article, User user){

        return ArticleResponseDTO.builder()
                .likeCount(article.getLikeCount())
                .text(article.getText())
                .id(article.getId())
                .userId(article.getAuthor().getId())
                .title(article.getTitle())
                .isLiked(user.getLikedArticles().contains(article))
                .authorName(article.getAuthor().getFirstName()+ " " +article.getAuthor().getFirstName())
                .build();
    }




    public List<ArticleResponseDTO> articlesToDTO(Collection<Article> articles,User user){
        List<ArticleResponseDTO> dtoList = new ArrayList<>(articles.size());
        for(Article article:articles){
            dtoList.add(ArticleResponseDTO.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .text(article.getText())
                    .authorName(article.getAuthor().getFirstName()+" " + article.getAuthor().getLastName())
                    .likeCount(article.getLikeCount())
                    .userId(article.getAuthor().getId())
                    .isLiked(user.getLikedArticles().contains(article))
                    .build());
        }
        return dtoList;
    }



    public List<UserResponseDTO> usersToDTO(Collection<User> users,User user){
        List<UserResponseDTO> dtos = new ArrayList<>(users.size());
        for(User u:users){
            dtos.add(
                    UserResponseDTO.builder()
                            .id(u.getId())
                            .firstName(u.getFirstName())
                            .lastName(u.getLastName())
                            .email(u.getEmail())
                            .description(u.getDescription())
                            .posts(u.getMyArticles().size())
                            .dateOfBirth(u.getDateOfBirth())
                            .mainGenre(u.getMainGenre())
                            .isLiked(u.getFollowedUsers().contains(user))
                            .build());
        }
        return dtos;
    }

    public List<UserResponseDTO> getFollowed(User u){
        Set<User> users = u.getFollowedUsers();
        List<UserResponseDTO> dtos = new ArrayList<>(users.size());
        for(User user:users){
            dtos.add(
             UserResponseDTO.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .description(user.getDescription())
                    .posts(user.getMyArticles().size())
                    .dateOfBirth(user.getDateOfBirth())
                    .mainGenre(user.getMainGenre())
                    .isLiked(user.getFollowedUsers().contains(u))
                    .build());
        }
        return dtos;
    }
}
