package com.mathinjection.authservice.dto;

import com.mathinjection.authservice.model.UserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GetUserResponseDto extends BaseResponseDto{
    private UserModel user;
}
