package com.mathinjection.authservice.configuration;

import com.mathinjection.authservice.service.UserService;
import com.mathinjection.authservice.util.validators.EmailValidator;
import com.mathinjection.authservice.util.validators.PasswordValidator;
import com.mathinjection.authservice.util.validators.RegisterReqDtoValidator;
import com.mathinjection.authservice.util.validators.UsernameValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean("TestUsernameValidator")
    public UsernameValidator usernameValidator() {
        return new UsernameValidator(userService());
    }

    @Bean("TestPasswordValidator")
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }

    @Bean("TestRegisterReqDtoValidator")
    RegisterReqDtoValidator registerReqDtoValidator() {
        return new RegisterReqDtoValidator(usernameValidator(), emailValidator(), passwordValidator());
    }

    @Bean("TestEmailValidator")
    public EmailValidator emailValidator() {
        return new EmailValidator(userService());
    }

    @Bean("TestUserService")
    public UserService userService() {
        return mock(UserService.class);
    }
}
