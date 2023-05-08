package com.example.jwt.exception;

public class TokenProcessingException extends RuntimeException {

    public TokenProcessingException(String message) {
        super(message);
    }
}
