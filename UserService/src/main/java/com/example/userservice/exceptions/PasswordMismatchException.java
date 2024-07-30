package com.example.userservice.exceptions;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String message)
    {
        super(message);
    }
}
