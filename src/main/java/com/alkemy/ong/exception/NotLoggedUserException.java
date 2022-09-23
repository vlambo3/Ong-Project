package com.alkemy.ong.exception;

public class NotLoggedUserException extends RuntimeException{

    public NotLoggedUserException(String message) {
        super(message);
    }

}
