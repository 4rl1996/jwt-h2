package com.example.jwt.service;

import com.example.jwt.data.JwtRequest;
import com.example.jwt.data.JwtResponse;

public interface AuthService {

    /**
     * Получить jwt-токен
     * @param authRequest пара username/password
     * @return jwt-токен
     */
    JwtResponse login(JwtRequest authRequest);
}
