package com.example.jwt.service;

import com.example.jwt.dao.entity.UserEntity;

public interface UserService {

    UserEntity getByEmail(String email);
}
