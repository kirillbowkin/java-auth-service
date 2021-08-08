package com.mathinjection.authservice.model;

import com.mathinjection.authservice.entity.RoleEntity;

import java.util.UUID;

public class FlatRoleModel {
    private UUID id;
    private String name;

    public FlatRoleModel(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FlatRoleModel EntityToModel(RoleEntity roleEntity) {
        return new FlatRoleModel(roleEntity.getId(), roleEntity.getName());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
