package com.mathinjection.authservice.dto;

import com.mathinjection.authservice.model.FlatRoleModel;
import com.mathinjection.authservice.model.RoleModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GetRolesResponseDto extends BaseResponseDto {
    private List<RoleModel> roles;
}
