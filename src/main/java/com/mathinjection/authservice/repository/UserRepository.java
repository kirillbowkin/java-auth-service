package com.mathinjection.authservice.repository;

import com.mathinjection.authservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserByUsername(String username);
    Optional<UserEntity> findUserByEmail(String email);
    Optional<UserEntity> findUserById(UUID id);

    @Modifying
    @Query(value = "insert into users_roles (user_id, role_id) values (:userId, :roleId)", nativeQuery = true)
    @Transactional
    void addRoleToUser(@Param("userId") UUID userId, @Param("roleId")UUID roleId);

    @Modifying
    @Query(value = "delete from users_roles where user_id = :userId and role_id = :roleId", nativeQuery = true)
    @Transactional
    void deleteRoleFromUser(@Param("userId") UUID userId, @Param("roleId")UUID roleId);

}
