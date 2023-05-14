package com.example.jwt.service.impl;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.data.JwtRequest;
import com.example.jwt.data.JwtResponse;
import com.example.jwt.exception.AuthException;
import com.example.jwt.service.AuthService;
import com.example.jwt.service.TokenService;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(JwtRequest authRequest) {
        final UserEntity user = userService.getByUsername(authRequest.getUsername()); // ищем юзера в нашей БД
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) { // сверяем пароли
            final String accessToken = tokenService.generateToken(user); // собираем токен
            return new JwtResponse(accessToken);
        } else {
            throw new AuthException("Invalid username/password value");
        }
    }
}
