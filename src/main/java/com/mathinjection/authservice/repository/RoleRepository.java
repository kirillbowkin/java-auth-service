package com.mathinjection.authservice.repository;

import com.mathinjection.authservice.entity.RoleEntity;
import org.hibernate.jpa.TypedParameterValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findRoleByName(String name);

    @Modifying
    @Query(value = "insert into roles_authorities (role_id, authority_id) values (:roleId, :authorityId)", nativeQuery = true)
    @Transactional
    void addAuthorityToRole(@Param("roleId") UUID roleId, @Param("authorityId") UUID authorityId);

    @Modifying
    @Query(value = "delete from roles_authorities where role_id = :roleId AND authority_id = :authorityId", nativeQuery = true)
    @Transactional
    void deleteAuthorityFromRole(@Param("roleId") UUID roleId, @Param("authorityId") UUID authorityId);
}
