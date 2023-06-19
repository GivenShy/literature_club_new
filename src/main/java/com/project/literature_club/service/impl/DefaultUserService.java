package com.project.literature_club.service.impl;

import com.project.literature_club.dtos.user.UpdateUserDTO;
import com.project.literature_club.dtos.user.UserResponseDTO;
import com.project.literature_club.entity.Role;
import com.project.literature_club.entity.User;
import com.project.literature_club.repositories.UserRepository;
import com.project.literature_club.service.JwtService;
import com.project.literature_club.service.UserService;
import com.project.literature_club.util.DTOMapper;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import com.project.literature_club.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final DTOMapper dtoMapper;

    public DefaultUserService(UserRepository authorRepository, JwtService jwtService, DTOMapper dtoMapper) {
        this.userRepository = authorRepository;
        this.jwtService = jwtService;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public User getByToken(String token) {
        Claims claims = jwtService.extractAllClaims(token.split(" ")[1].trim());
        String username = claims.getSubject();
        return findByEmail(username).get();

    }

    @Override
    public List<UserResponseDTO> followedUsers(String token){
        User user = getByToken(token);
        return dtoMapper.usersToDTO(user.getFollowedUsers(),user);
    }

    @Override
    public void follow(long id, String token) {
        User user = getByToken(token);
        User otherUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.getFollowedUsers().add(otherUser);
        save(user);
    }

    @Override
    public void unfollow(long id,String token) {
        User user = getByToken(token);
        User otherUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.getFollowedUsers().remove(otherUser);
        save(user);
    }

    @Override
    public UserResponseDTO update(String token, UpdateUserDTO dto){
        User user = getByToken(token);
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.firstName);
        user.setLastName(dto.getLastName());
        save(user);
        return dtoMapper.convertUserToDTO(user,user);
    }

    @Override
    public UserResponseDTO getUserById(String token, long id){
        User user = getByToken(token);
        User otherUser = get(id);
        return dtoMapper.convertUserToDTO(otherUser,user);
    }
    @Override
    public UserResponseDTO getMyUser(String token){
        User user = getByToken(token);
        return dtoMapper.convertUserToDTO(user,user);
    }
    @Override
    public List<UserResponseDTO> getAll(String token){
        User user = getByToken(token);
        List<User> users = userRepository.findAllByIdIsNot(user.getId());
        return dtoMapper.convertUserToDTOList(users,user);
    }
    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User get(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(Long id, User author) {
        var oldUser = this.get(id);
        if (author.getFirstName() != null) {
            oldUser.setFirstName(author.getFirstName());
        }
        if (author.getLastName() != null) {
            oldUser.setLastName(author.getLastName());
        }
        if (author.getEmail() != null) {
            oldUser.setEmail(author.getEmail());
        }
        this.save(oldUser);
        return oldUser;
    }



    @Override
    public void delete(String token){
        User user = getByToken(token);
        userRepository.delete(user);
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.orElseThrow(UserNotFoundException::new));
    }

    public List<User> search(String str) {
        return userRepository.searchUser(str.toLowerCase());
    }

    public List<User> findAllAuthors() {
        return userRepository.findAllByRole(Role.AUTHOR);
    }

    @Override
    public List<User> findAllExcept(Long id) {
        return userRepository.findAllByIdIsNot(id);
    }

    @Override
    public List<User> findFollowedUsers(Long id) {
        return userRepository.followedAuthors(id);
    }


}