package com.mathinjection.authservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRoleDto {
    UUID userId;
    UUID roleId;
}
