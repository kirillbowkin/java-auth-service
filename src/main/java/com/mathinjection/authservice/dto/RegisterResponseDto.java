package com.mathinjection.authservice.dto;

import com.mathinjection.authservice.model.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterResponseDto extends BaseResponseDto {
    User user;
}
