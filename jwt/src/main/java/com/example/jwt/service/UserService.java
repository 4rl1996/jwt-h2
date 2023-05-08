package com.example.jwt.service;

import com.example.jwt.dao.entity.UserEntity;

public interface UserService {

    UserEntity getByUsername(String username);
}
