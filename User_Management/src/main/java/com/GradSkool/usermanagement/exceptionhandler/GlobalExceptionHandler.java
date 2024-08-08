package com.GradSkool.usermanagement.exceptionhandler;

import com.GradSkool.usermanagement.Exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException notFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested Data Not Found");
    }
}
