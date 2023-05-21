package com.example.jwt.service;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.data.RegistrationRequest;

public interface UserService {

    /**
     * Получить юзера из БД
     *
     * @param username параметр запроса
     * @return сущность из БД
     */
    UserEntity getByUsername(String username);

    UserEntity addCandidate(RegistrationRequest registrationRequest);

    UserEntity addHr(RegistrationRequest registrationRequest);

    void deleteEmployee(String username);

    UserEntity addManager(RegistrationRequest registrationRequest);
}
