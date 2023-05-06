package com.example.jwt.repo;

import com.example.jwt.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE u.email = :email")
    UserEntity findByEmail(@Param("email") String email);
}
