package com.example.jwt.exception;

import com.example.jwt.notificator.TelegramNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;



@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class JwtExampleExceptionHandler {

    private final TelegramNotificationService telegramNotificationService;

    private static final String JWT_ERROR_MESSAGE = "FORBIDDEN. RE-LOGIN OR CHECK TOKEN";

    @ExceptionHandler(AuthException.class)
    public ResponseEntity authExceptionHandler(AuthException ex) {
        sendNotification(ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundExceptionHandler(UserNotFoundException ex) {
        sendNotification(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TokenProcessingException.class)
    public ResponseEntity jwtExceptionHandler(TokenProcessingException ex) {
        sendNotification(ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(JWT_ERROR_MESSAGE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity commonExceptionHandler(Exception ex) {
        sendNotification(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

   private void sendNotification(Exception ex) {
        String exClass = "Exception class: " + ex.getClass().toString();
        String exMessage = "Exception message: " + ex.getMessage();
        String exFirstStackTrace = "First stack trace: " + Arrays.stream(ex.getStackTrace()).findFirst().toString();

        String notificationMessage = exClass +
                "\n\n\n" +
                exMessage +
                "\n\n\n" +
                exFirstStackTrace;

        telegramNotificationService.sendMessage(notificationMessage);
    }
}
