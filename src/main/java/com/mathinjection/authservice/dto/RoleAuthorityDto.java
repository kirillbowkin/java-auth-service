package com.mathinjection.authservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RoleAuthorityDto {
    private UUID roleId;
    private UUID authorityId;
}
