package com.example.jwt.service.impl;

import com.example.jwt.dao.Role;
import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.dao.repository.UserRepo;
import com.example.jwt.data.RegistrationRequest;
import com.example.jwt.exception.UserNotFoundException;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;


    @Override
    public UserEntity getByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User was not found"));
    }

    @Override
    public UserEntity addCandidate(RegistrationRequest registrationRequest) {
        checkPasswordIdentity(registrationRequest);
        UserEntity entityToSave = UserEntity.builder()
                .roles(Set.of(Role.CANDIDATE))
                .username(registrationRequest.getUsername())
                .password(encoder.encode(registrationRequest.getFirstPassword()))
                .build();
        return userRepo.save(entityToSave);    }

    @Override
    public UserEntity addHr(RegistrationRequest registrationRequest) {
        checkPasswordIdentity(registrationRequest);
        UserEntity entityToSave = UserEntity.builder()
                .roles(Set.of(Role.HR))
                .username(registrationRequest.getUsername())
                .password(encoder.encode(registrationRequest.getFirstPassword()))
                .build();
        return userRepo.save(entityToSave);
    }

    @Override
    public void deleteEmployee(String username) {
        userRepo.deleteByUsername(username);
    }

    @Override
    public UserEntity addManager(RegistrationRequest registrationRequest) {
        checkPasswordIdentity(registrationRequest);
        UserEntity entityToSave = UserEntity.builder()
                .roles(Set.of(Role.MANAGER))
                .username(registrationRequest.getUsername())
                .password(encoder.encode(registrationRequest.getFirstPassword()))
                .build();
        return userRepo.save(entityToSave);
    }

    private void checkPasswordIdentity(RegistrationRequest registrationRequest) {
        if (userRepo.existsByUsername(registrationRequest.getUsername())) {
            throw new RuntimeException("USER ALREADY EXISTS");
        }
        if (!registrationRequest.getFirstPassword().equals(registrationRequest.getSecondPassword())) {
            throw new RuntimeException("PASSWORDS ARE NOT EQUAL");
        }
    }
}
