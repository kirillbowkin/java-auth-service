package com.mathinjection.authservice.repository;

import com.mathinjection.authservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserByUsername(String username);
    Optional<UserEntity> findUserByEmail(String email);
    Optional<UserEntity> findUserById(UUID id);
}
