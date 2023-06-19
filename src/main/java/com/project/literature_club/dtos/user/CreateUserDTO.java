package com.project.literature_club.dtos.user;

import com.project.literature_club.entity.Role;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

public class CreateUserDTO {
    @Getter
    @Setter
    public long id;

    @Getter
    @Setter
    public String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    @Email
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Role role;

    @Getter
    @Setter
    private String description;
}
