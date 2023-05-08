package com.example.jwt.controller;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')") // одна из ролей
    @PostMapping("/user")
    public ResponseEntity<String> loginUser() {
        return ResponseEntity.ok("Good day, user!");
    }

    @PreAuthorize("hasAuthority('USER')") // для роли ADMIN
    @GetMapping("/admin")
    public UserEntity getUser(String email) {
        return userService.getByUsername(email);
    }




}
