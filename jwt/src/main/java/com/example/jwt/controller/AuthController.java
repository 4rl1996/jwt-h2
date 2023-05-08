package com.example.jwt.controller;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.data.JwtRequest;
import com.example.jwt.data.JwtResponse;
import com.example.jwt.service.AuthService;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/entry")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) {
        final var response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/kek")
    public UserEntity getUser(String email) {
        return userService.getByUsername(email);
    }


//    @PostMapping("/registration")



}
