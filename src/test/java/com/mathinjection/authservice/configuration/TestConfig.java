package com.mathinjection.authservice.configuration;

import com.mathinjection.authservice.service.UserService;
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
    public UserService userService() {
        return mock(UserService.class);
    }
}
