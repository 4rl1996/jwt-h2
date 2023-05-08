package com.example.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class JwtExampleExceptionHandler {

    private static final String JWT_ERROR_MESSAGE = "FORBIDDEN. RE-LOGIN OR CHECK TOKEN";

    @ExceptionHandler(AuthException.class)
    public ResponseEntity authExceptionHandler(AuthException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundExceptionHandler(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TokenProcessingException.class)
    public ResponseEntity jwtExceptionHandler(TokenProcessingException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(JWT_ERROR_MESSAGE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity commonExceptionHandler(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
