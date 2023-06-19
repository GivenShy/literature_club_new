package com.project.literature_club.exceptions;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User is not found");
    }
}
