package com.example.jwt.service.impl;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.repo.UserRepo;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserEntity getByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
