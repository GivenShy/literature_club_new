package com.project.literature_club.dtos.authentication;


import com.project.literature_club.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String mainGenre;
    private Date dateOfBirth;
    private String description;
    private Role role;
}
