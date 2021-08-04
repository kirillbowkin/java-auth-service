package com.mathinjection.authservice.util.validators;

import com.mathinjection.authservice.configuration.TestConfig;
import com.mathinjection.authservice.model.User;
import com.mathinjection.authservice.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@DisplayName("Username Validation")
class UsernameValidatorTest {
    @Autowired
    private UsernameValidator usernameValidator;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Should Reject Username If User With This Username In Database")
    public void ShouldRejectUsernameIfItPersistsInDB() {
        final String username = "whatever";
        when(userService.findByUsername(username)).thenReturn(new User());

        assertFalse(usernameValidator.validate(username).isEmpty());
    }
}