package com.mathinjection.authservice.dto;

import com.mathinjection.authservice.entity.UserEntity;
import com.mathinjection.authservice.model.UserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RegisterResponseDto extends BaseResponseDto {
    UserModel user;
}
