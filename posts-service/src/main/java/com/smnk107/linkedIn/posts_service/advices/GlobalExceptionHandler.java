package com.smnk107.linkedIn.posts_service.advices;

import com.smnk107.linkedIn.posts_service.exceptions.BadRequestException;
import com.smnk107.linkedIn.posts_service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
 ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(String message)
 {
    ApiError  apiError = ApiError.builder()
            .statusCode(HttpStatus.NOT_FOUND)
            .error(message)
            .build();

    return buildErrorResponseEntity(apiError);
 }

 @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ApiResponse<?>> handleBadRequestException(String message)
    {
        ApiError  apiError = ApiError.builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .error(message)
                .build();

        return buildErrorResponseEntity(apiError);
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex) {
        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        //return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());

        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
