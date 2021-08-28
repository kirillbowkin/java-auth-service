package com.mathinjection.authservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddRoleDto {
    UUID userId;
    UUID roleId;
}
