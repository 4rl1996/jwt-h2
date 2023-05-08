package com.example.jwt.dao.repository;

import com.example.jwt.dao.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

//    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username")
//    Optional<UserEntity> findByUsername(@Param("username") String username);
}
