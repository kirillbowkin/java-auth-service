package com.mathinjection.authservice.model;

import com.mathinjection.authservice.entity.RoleEntity;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoleModel extends FlatRoleModel{
    private Collection<FlatUserModel> users;

    public RoleModel(UUID id, String name, Collection<FlatUserModel> users) {
        super(id, name);
        this.users = users;
    }


    public static RoleModel EntityToModel(RoleEntity roleEntity) {
        return new RoleModel(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getUsers().stream().map(FlatUserModel::EntityToModel).collect(Collectors.toList()));
    }

    public Collection<FlatUserModel> getUsers() {
        return users;
    }

    public void setUsers(Collection<FlatUserModel> users) {
        this.users = users;
    }
}
