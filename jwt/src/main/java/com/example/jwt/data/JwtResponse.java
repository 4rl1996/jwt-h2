package com.example.jwt.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";

    private String accessToken;
}
