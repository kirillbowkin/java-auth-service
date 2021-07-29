package com.mathinjection.authservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthenticationReqDto extends BaseResponseDto {
    private String username;
    private String password;
}
