package com.example.jwt.dao.entity;

import com.example.jwt.dao.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "service_user")
@Data
public class UserEntity {

    @Id
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_name")
    private Set<Role> roles;
}
