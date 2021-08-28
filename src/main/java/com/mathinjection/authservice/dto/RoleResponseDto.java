package com.mathinjection.authservice.dto;

import com.mathinjection.authservice.model.RoleModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RoleResponseDto extends BaseResponseDto {
    private RoleModel role;
}
