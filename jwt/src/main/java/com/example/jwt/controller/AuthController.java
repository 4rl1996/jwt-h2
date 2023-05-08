package com.example.jwt.controller;

import com.example.jwt.data.JwtRequest;
import com.example.jwt.data.JwtResponse;
import com.example.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/entry")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login") // получить jwt-токен
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) {
        final var response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
