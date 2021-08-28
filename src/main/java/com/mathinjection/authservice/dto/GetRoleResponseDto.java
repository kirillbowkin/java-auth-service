package com.mathinjection.authservice.dto;

import com.mathinjection.authservice.model.FlatRoleModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GetRoleResponseDto extends BaseResponseDto {
    private FlatRoleModel role;
}
