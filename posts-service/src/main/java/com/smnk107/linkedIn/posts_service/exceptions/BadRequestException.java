package com.smnk107.linkedIn.posts_service.exceptions;


public class BadRequestException extends RuntimeException{

    public BadRequestException(String message)
    {
        super(message);
    }
}
