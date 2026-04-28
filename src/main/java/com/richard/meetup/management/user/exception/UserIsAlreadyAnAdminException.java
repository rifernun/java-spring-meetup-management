package com.richard.meetup.management.user.exception;

public class UserIsAlreadyAnAdminException extends RuntimeException {
    public UserIsAlreadyAnAdminException(String message) {
        super(message);
    }
}
