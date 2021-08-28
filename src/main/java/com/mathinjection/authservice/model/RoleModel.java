package com.mathinjection.authservice.model;

import com.mathinjection.authservice.entity.RoleEntity;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoleModel extends FlatRoleModel{
    private Collection<FlatAuthorityModel> authorityModels;

    public RoleModel(UUID id, String name, Collection<FlatAuthorityModel> authorityModels) {
        super(id, name);
        this.authorityModels = authorityModels;
    }


    public static RoleModel EntityToModel(RoleEntity roleEntity) {
        return new RoleModel(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getAuthorities().stream().map(FlatAuthorityModel::EntityToModel).collect(Collectors.toList()));
    }

    public Collection<FlatAuthorityModel> getAuthorities() {
        return authorityModels;
    }

    public void setAuthorities(Collection<FlatAuthorityModel> users) {
        this.authorityModels = users;
    }
}
