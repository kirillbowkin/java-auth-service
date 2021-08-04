package com.mathinjection.authservice.configuration;

import com.mathinjection.authservice.service.UserService;
import com.mathinjection.authservice.util.validators.EmailValidator;
import com.mathinjection.authservice.util.validators.PasswordValidator;
import com.mathinjection.authservice.util.validators.UsernameValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean
    public UsernameValidator usernameValidator() {
        return new UsernameValidator(userService());
    }

    @Bean
    public PasswordValidator passwordValidator() { return new PasswordValidator(); }

    @Bean
    public EmailValidator emailValidator() { return new EmailValidator(userService()); }

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }
}
