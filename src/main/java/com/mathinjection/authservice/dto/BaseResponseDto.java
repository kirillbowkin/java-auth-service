package com.mathinjection.authservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public abstract class BaseResponseDto {
    private ResponseStatus status;
    private String path;
    private LocalDateTime timestamp;
}
