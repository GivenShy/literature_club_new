package com.project.literature_club.service;

import com.project.literature_club.dtos.user.UpdateUserDTO;
import com.project.literature_club.dtos.user.UserResponseDTO;
import com.project.literature_club.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getByToken(String token);

    List<UserResponseDTO> followedUsers(String token);

    void follow(long id, String token);

    void unfollow(long id, String token);

    UserResponseDTO update(String token, UpdateUserDTO dto);

    UserResponseDTO getUserById(String token, long id);

    UserResponseDTO getMyUser(String token);

    List<UserResponseDTO> getAll(String token);

    Optional<User> findById(int id);

 Optional<User> findByEmail(String email);

    User get(Long id);

    User save(User user);

    User update(Long id, User author);

    void delete(String token);

    void delete(Long id);

    List<User> findAllExcept(Long id);

    List<User> findFollowedUsers(Long id);
}
