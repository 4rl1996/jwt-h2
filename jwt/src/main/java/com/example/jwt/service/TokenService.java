package com.example.jwt.service;

import com.example.jwt.dao.entity.UserEntity;
import io.jsonwebtoken.Claims;

public interface TokenService {

    String generateToken(UserEntity user);

    boolean validateAccessToken(String token);

    Claims getClaims(String token);
}
