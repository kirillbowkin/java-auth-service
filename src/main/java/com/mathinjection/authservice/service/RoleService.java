package com.mathinjection.authservice.service;

import com.mathinjection.authservice.entity.RoleEntity;
import com.mathinjection.authservice.model.FlatRoleModel;
import com.mathinjection.authservice.model.RoleModel;
import com.mathinjection.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.PostgresUUIDType;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public List<RoleModel> findAll() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities.stream().map(RoleModel::EntityToModel).collect(Collectors.toList());
    }

    public RoleModel findModelByRoleName(String roleName) throws RoleNotFoundException {
        return RoleModel.EntityToModel(roleRepository.findRoleByName(roleName).orElseThrow(() -> new RoleNotFoundException(new StringBuilder().append("Role with name ").append(roleName).append(" was not found").toString())));
    }

    public RoleModel findModelById(UUID roleId) throws RoleNotFoundException {
        return RoleModel.EntityToModel(roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(new StringBuilder().append("Role with id ").append(roleId).append(" was not found").toString())));
    }

    public void addAuthorityToRole(UUID roleId, UUID authorityId) {
        roleRepository.addAuthorityToRole(roleId, authorityId);
    }

    public void deleteAuthorityFromRole(UUID roleId, UUID authorityId) {
        roleRepository.deleteAuthorityFromRole(roleId, authorityId);
    }
}
