package com.mathinjection.authservice.util.validators;

import com.mathinjection.authservice.dto.RegisterReqDto;
import com.mathinjection.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegisterReqDtoValidator implements CustomValidator<RegisterReqDto> {

    private final UsernameValidator usernameValidator;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;

    @Override
    public List<Map<String, String>> validate(RegisterReqDto registerReqDto) {
        List<Map<String, String>> result = new ArrayList<>();

        result.addAll(usernameValidator.validate(registerReqDto.getUsername()));
        result.addAll(emailValidator.validate(registerReqDto.getEmail()));
        result.addAll(passwordValidator.validate(registerReqDto.getPassword()));

        return result;
    }
}
