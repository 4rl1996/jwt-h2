package com.example.jwt.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    HR("HR"),
    CANDIDATE("CANDIDATE"),
    MANAGER("MANAGER");


    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
