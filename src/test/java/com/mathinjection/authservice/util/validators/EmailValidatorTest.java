package com.mathinjection.authservice.util.validators;

import com.mathinjection.authservice.configuration.TestConfig;
import com.mathinjection.authservice.entity.UserEntity;
import com.mathinjection.authservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Map;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@DisplayName("Email Validator")
class EmailValidatorTest {
    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private UserService userService;

    @AfterEach
    public void mockito_reset() {
        Mockito.reset(userService);
    }

    @Test
    @DisplayName("Should give error that email already exists")
    public void shouldGiveErrorIfEmailAlreadyExists() {
        when(userService.findByEmail(anyString())).thenReturn(new UserEntity());

        assertTrue(emailValidator.validate("whatever").stream().anyMatch(
                error -> error.containsKey("error") && error.get("error").equals("invalid email")
                        && error.containsKey("message") && error.get("message").equals("user with this email already exists")
        ));
    }

    @Test
    @DisplayName("Should not give error that email already exists")
    public void shouldNotGiveErrorIfEmailAlreadyExists() {
        when(userService.findByEmail(anyString())).thenThrow(new UsernameNotFoundException(""));

        Predicate<Map<String, String>> emailExistsPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid email")
                && error.containsKey("message") && error.get("message").equals("user with this email already exists");

        assertFalse(emailValidator.validate("whatever").stream().anyMatch(
                emailExistsPredicate
        ));
    }

    @Test
    @DisplayName("Should fail if email not matches pattern")
    public void shouldFailIfEmailNotMatchesPatter() {
        when(userService.findByEmail(anyString())).thenThrow(new UsernameNotFoundException(""));

        Predicate<Map<String, String>> patternNotMatchPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid email")
                && error.containsKey("message") && error.get("message").equals("invalid email format");

        Predicate<Map<String, String>> emailIsNullPredicate = error -> error.containsKey("error") && error.get("error").equals("email is obligatory")
                && error.containsKey("message") && error.get("message").equals("email is obligatory");

        assertTrue(emailValidator.validate("").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("rewrew").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("43243").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("......").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("%%%%%%%").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("-------").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("________").stream().anyMatch(patternNotMatchPredicate));

        assertTrue(emailValidator.validate("rewrew.fdsjkl").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("43243.fdsjkl").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate(".......fdsjkl").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("%%%%%%%.fdsjkl").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("-------.fdsjkl").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("________.fdsjkl").stream().anyMatch(patternNotMatchPredicate));

        assertTrue(emailValidator.validate("rewrew@.com").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("43243@.com").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("......@.com").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("%%%%%%%@.com").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("-------@.com").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(emailValidator.validate("________@.com").stream().anyMatch(patternNotMatchPredicate));

        assertTrue(emailValidator.validate("fjsdk@gmail.comjjkljkjljljlkjlfffffffffffffffffffff").stream().anyMatch(patternNotMatchPredicate));

    }

    @Test
    @DisplayName("Should fail if email matches pattern but does exist in db")
    public void shouldFailIfEmailMatchesPatternButInDb() {
        Predicate<Map<String, String>> emailExistsPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid email")
                && error.containsKey("message") && error.get("message").equals("user with this email already exists");

        when(userService.findByEmail(anyString())).thenReturn(new UserEntity());

        assertTrue(emailValidator.validate("fassfd@fjf.fddd").stream().anyMatch(emailExistsPredicate));
        assertTrue(emailValidator.validate("test@gogle.com").stream().anyMatch(emailExistsPredicate));
        assertTrue(emailValidator.validate("43sfd@fjf.fddd").stream().anyMatch(emailExistsPredicate));
        assertTrue(emailValidator.validate("fsfd_@jf.fddd").stream().anyMatch(emailExistsPredicate));
        assertTrue(emailValidator.validate("fsfd%@jf.fddd").stream().anyMatch(emailExistsPredicate));
        assertTrue(emailValidator.validate("fsfd-@jf.fddd").stream().anyMatch(emailExistsPredicate));
        assertTrue(emailValidator.validate("fsfd.@jf.fddd").stream().anyMatch(emailExistsPredicate));
    }

    @Test
    @DisplayName("Should pass if email matches pattern and does not exist in db")
    public void shouldPassIfEmailMatchesPatternAndDoesNotExistsInDb() {
        when(userService.findByEmail(anyString())).thenThrow(new UsernameNotFoundException(""));

        assertTrue(emailValidator.validate("fassfd@fjf.fddd").isEmpty());
        assertTrue(emailValidator.validate("test@gogle.com").isEmpty());
        assertTrue(emailValidator.validate("43sfd@fjf.fddd").isEmpty());
        assertTrue(emailValidator.validate("fsfd_@jf.fddd").isEmpty());
        assertTrue(emailValidator.validate("fsfd%@jf.fddd").isEmpty());
        assertTrue(emailValidator.validate("fsfd-@jf.fddd").isEmpty());
        assertTrue(emailValidator.validate("fsfd.@jf.fddd").isEmpty());
    }
}