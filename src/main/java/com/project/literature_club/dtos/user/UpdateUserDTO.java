package com.project.literature_club.dtos.user;

import lombok.Getter;
import lombok.Setter;

public class UpdateUserDTO {

    @Getter
    @Setter
    public String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String email;

}
