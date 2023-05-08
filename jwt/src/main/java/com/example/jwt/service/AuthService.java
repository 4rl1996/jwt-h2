package com.example.jwt.service;

import com.example.jwt.data.JwtRequest;
import com.example.jwt.data.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest authRequest);

    void logout();
}
