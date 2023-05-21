package com.example.jwt.data;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String username;
    private String firstPassword;
    private String secondPassword;
}
