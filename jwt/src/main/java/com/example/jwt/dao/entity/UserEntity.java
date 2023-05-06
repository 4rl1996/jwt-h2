package com.example.jwt.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service_user")
@Data
public class UserEntity {

    @Id
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
