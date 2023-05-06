package com.example.jwt.data;

import lombok.Data;

@Data
public class JwtRequest {

    private String email;

    private String password;
}
