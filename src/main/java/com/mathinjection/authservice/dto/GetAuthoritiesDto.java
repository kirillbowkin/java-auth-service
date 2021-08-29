package com.mathinjection.authservice.dto;

import com.mathinjection.authservice.model.FlatAuthorityModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GetAuthoritiesDto extends BaseResponseDto{
    private List<FlatAuthorityModel> authorities;
}
