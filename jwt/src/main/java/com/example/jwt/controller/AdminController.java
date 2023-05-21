package com.example.jwt.controller;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.data.RegistrationRequest;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;

    @PostMapping("/hr")
    public ResponseEntity<UserEntity> addHr(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addHr(registrationRequest));
    }

    @PostMapping("/manager")
    public ResponseEntity<UserEntity> addManager(@RequestBody RegistrationRequest registrationRequest) {
       return ResponseEntity.status(HttpStatus.CREATED).body(userService.addManager(registrationRequest));
    }

    @DeleteMapping("/employee/{username}")
    @ResponseBody()
    public void deleteHr(@PathVariable("username") String username) {
        userService.deleteEmployee(username);
    }
}
