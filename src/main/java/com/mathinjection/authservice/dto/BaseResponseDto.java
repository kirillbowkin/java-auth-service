package com.mathinjection.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(type = "object", name = "Response super class", subTypes = {RegisterResponseDto.class, ErrorResponseDto.class})
@Data
@Accessors(chain = true)
public abstract class BaseResponseDto {
    private ResponseStatus status;
    private String path;
    private LocalDateTime timestamp;
}
