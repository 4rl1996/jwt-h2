package com.example.jwt.data;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;

    private String password;
}
