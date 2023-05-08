package com.example.jwt.util.keyGenerator;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class KeyGenerator {

    /**
     * Получить ключ для подписи токена, чтобы потом его использовать
     * Технический класс, напрямую не используемый в сервисе
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(
                Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded())
        );
    }
}
