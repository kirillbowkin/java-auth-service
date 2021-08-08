package com.mathinjection.authservice.model;

import com.mathinjection.authservice.entity.UserEntity;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserModel extends FlatUserModel {
    private Collection<FlatRoleModel> roles;

    public UserModel(UUID id, String username, String password, String email, Collection<FlatRoleModel> roles) {
        super(id, username, password, email);
        this.roles = roles;
    }

    public static UserModel EntityToModel(UserEntity userEntity) {
        return new UserModel(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getRoles().stream().map(FlatRoleModel::EntityToModel).collect(Collectors.toList())
        );
    }

    public Collection<FlatRoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Collection<FlatRoleModel> roles) {
        this.roles = roles;
    }
}
