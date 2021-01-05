package com.example.demo.post.exception;

import com.example.demo.common.ErrorResponse;
import com.example.demo.post.exception.exceptions.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class PostExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse unknownError(Throwable throwable){
        return new ErrorResponse(HttpStatus.NOT_FOUND, "0000", throwable.getMessage());
    }


    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse reportError(PostNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, "0001", exception.getMessage());
    }
}
