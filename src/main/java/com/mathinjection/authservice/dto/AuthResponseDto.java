package com.mathinjection.authservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AuthResponseDto extends BaseResponseDto {
    private Map<String, String> tokens;
}
