package com.example.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound()
    {
        return new ResponseEntity<>("User Not found", HttpStatus.OK);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<String> passwordNotMatching()
    {
        return new ResponseEntity<>("Password does not match", HttpStatus.OK);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<String> tokenNotFoundException()
    {
        return new ResponseEntity<>("Password does not match", HttpStatus.OK);
    }
}
