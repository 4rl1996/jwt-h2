package com.example.jwt.util.keyGenerator;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class KeyGenerator {

    public static void main(String[] args) {
        System.out.println(
                Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded())
        );
    }
}
