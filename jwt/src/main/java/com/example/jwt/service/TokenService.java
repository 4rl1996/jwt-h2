package com.example.jwt.service;

import com.example.jwt.dao.entity.UserEntity;
import io.jsonwebtoken.Claims;

public interface TokenService {

    /**
     * Создание токена для юзера
     * @param user для которого нужно собрать токен
     * @return строка токена
     */
    String generateToken(UserEntity user);

    /**
     * Проверка токена
     * @param token который нужно проверить
     * @return результат проверки в формате булева значения
     */
    boolean validateAccessToken(String token);

    /**
     * Получить объект с полями токена
     * @param token который нужно разобрать
     * @return объект с полями токена
     */
    Claims getClaims(String token);
}
