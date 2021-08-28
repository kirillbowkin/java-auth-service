package com.mathinjection.authservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AddRoleDto {
    private String roleName;
    private List<UUID> authorities;
}
