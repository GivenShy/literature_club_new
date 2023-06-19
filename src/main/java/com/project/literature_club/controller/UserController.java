package com.project.literature_club.controller;

import com.project.literature_club.dtos.user.UpdateUserDTO;
import com.project.literature_club.dtos.user.UserResponseDTO;
import com.project.literature_club.entity.User;
import com.project.literature_club.service.ArticleService;
import com.project.literature_club.service.JwtService;
import com.project.literature_club.service.UserService;
import com.project.literature_club.util.DTOMapper;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService, ArticleService articleService, JwtService jwtService, DTOMapper dtoMapper) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAll(@RequestHeader("Authorization") String token) {
       return userService.getAll(token);
    }

    @GetMapping("/my")
    public UserResponseDTO getMyUser(@RequestHeader("Authorization") String token) {
        return userService.getMyUser(token);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable long id, @RequestHeader("Authorization") String token) {
        return userService.getUserById(token,id);
    }

    @PutMapping("/user")
    public UserResponseDTO updateUser(@RequestHeader("Authorization") String token, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.update(token,updateUserDTO);
    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String token) {
       userService.delete(token);
    }

}
