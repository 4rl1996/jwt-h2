package com.example.jwt.service;

import com.example.jwt.dao.entity.UserEntity;

public interface UserService {

    /**
     * Получить юзера из БД
     * @param username параметр запроса
     * @return сущность из БД
     */
    UserEntity getByUsername(String username);
}
