package com.mathinjection.authservice.dto;

import com.mathinjection.authservice.model.FlatAuthorityModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GetAuthorityDto extends BaseResponseDto {
    private FlatAuthorityModel authority;
}
