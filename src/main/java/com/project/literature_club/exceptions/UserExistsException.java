package com.project.literature_club.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super("User already exists");
    }
}
