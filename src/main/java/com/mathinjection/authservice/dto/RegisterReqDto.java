package com.mathinjection.authservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterReqDto {
    private final String username;
    private final String email;
    private final String password;
}
