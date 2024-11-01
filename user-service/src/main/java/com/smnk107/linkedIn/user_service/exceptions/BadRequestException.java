package com.smnk107.linkedIn.user_service.exceptions;


public class BadRequestException extends RuntimeException{

    public BadRequestException(String message)
    {
        super(message);
    }
}
