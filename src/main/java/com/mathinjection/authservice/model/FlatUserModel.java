package com.mathinjection.authservice.model;

import com.mathinjection.authservice.entity.UserEntity;

import java.util.UUID;

public class FlatUserModel {
    private UUID id;
    private String username;
    private String password;
    private String email;

    public FlatUserModel(UUID id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static FlatUserModel EntityToModel(UserEntity userEntity) {
        return new FlatUserModel(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
