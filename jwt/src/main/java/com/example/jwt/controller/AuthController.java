package com.example.jwt.controller;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.data.JwtRequest;
import com.example.jwt.data.JwtResponse;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/entry")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) {

        return ResponseEntity.ok(new JwtResponse("1"));
    }

    @GetMapping("/kek")
    public UserEntity getUser(String email) {
        return userService.getByEmail(email);
    }


//    @PostMapping("/registration")



}
