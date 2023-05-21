package com.example.jwt.controller;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.data.JwtRequest;
import com.example.jwt.data.JwtResponse;
import com.example.jwt.data.RegistrationRequest;
import com.example.jwt.service.AuthService;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entry")
public class EntryController {

    private final AuthService authService;
    private final UserService userService;
    private final MailController mailController;


    @PostMapping("/login") // получить jwt-токен
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registration")
    public ResponseEntity<JwtResponse> addCandidate(@RequestBody RegistrationRequest registrationRequest) {
        UserEntity userEntity = userService.addCandidate(registrationRequest);

        return ResponseEntity.ok(authService.login(JwtRequest.builder()
                .username(registrationRequest.getUsername())
                .password(registrationRequest.getFirstPassword())
                .build()));
    }
}
