package com.example.jwt.service.impl;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.dao.repository.UserRepo;
import com.example.jwt.exception.UserNotFoundException;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserEntity getByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User was not found"));
    }
}
