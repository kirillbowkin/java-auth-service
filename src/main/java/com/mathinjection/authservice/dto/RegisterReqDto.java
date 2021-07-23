package com.mathinjection.authservice.dto;

import lombok.Data;

@Data
public class RegisterReqDto {
    private final String username;
    private final String email;
    private final String password;
}
