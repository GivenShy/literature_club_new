package com.project.literature_club.controller;

import com.project.literature_club.dtos.user.UserResponseDTO;
import com.project.literature_club.entity.User;
import com.project.literature_club.exceptions.UserExistsException;
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
@RequestMapping("/follow")
public class FollowController {

    private final ArticleService articleService;
    private final UserService userService;
    private final JwtService jwtService;
    private final DTOMapper dtoMapper;

    @GetMapping
    public List<UserResponseDTO> followedUsers(@RequestHeader("Authorization") String token){
        return userService.followedUsers(token);

    }

    @PostMapping("/{id}")
    public boolean follow(@PathVariable long id,@RequestHeader("Authorization") String token){
        userService.follow(id,token);
        return true;

    }

    @DeleteMapping("/{id}")
    public boolean unfollow(@PathVariable long id,@RequestHeader("Authorization") String token){
        userService.unfollow(id,token);
        return true;

    }


}
