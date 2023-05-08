package com.example.jwt.service.impl;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.dao.repository.UserRepo;
import com.example.jwt.exception.AuthException;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
//    private final UserMapper userMapper;

    @Override
    public UserEntity getByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new AuthException("User was not found"));
    }
}
