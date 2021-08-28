package com.mathinjection.authservice.model;

import com.mathinjection.authservice.entity.AuthorityEntity;

import java.util.UUID;

public class FlatAuthorityModel {
    private UUID id;
    private String name;

    public FlatAuthorityModel(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FlatAuthorityModel EntityToModel(AuthorityEntity authorityEntity) {
        return new FlatAuthorityModel(authorityEntity.getId(), authorityEntity.getName());
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
