package com.mathinjection.authservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Map;

@Data
@Accessors(chain = true)
public class ErrorResponseDto extends BaseResponseDto {
    Collection<Map<String, String>> errors;
}
