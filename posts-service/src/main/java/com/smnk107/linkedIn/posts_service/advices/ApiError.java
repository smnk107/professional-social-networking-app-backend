package com.smnk107.linkedIn.posts_service.advices;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiError {

    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError()
    {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus statusCode)
    {
        this.error = error;
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now();
    }

    public HttpStatus getStatus()
    {
        return statusCode;
    }

}


