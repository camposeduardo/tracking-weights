package com.camposeduardo.trackingweights.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Email is already taken.");
    }
}
