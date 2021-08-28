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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@DisplayName("Username validation")
class UsernameValidatorTest {
    @Autowired
    private UsernameValidator usernameValidator;

    @Autowired
    private UserService userService;

    @AfterEach
    public void mockito_reset() {
        Mockito.reset(userService);
    }

    @Test
    @DisplayName("Should give error that username already exists")
    public void ShouldGiveErrorUserAlreadyExists() {
        final String username = "whatever";
        when(userService.findEntityByUsername(username)).thenReturn(new UserEntity());

        assertTrue(usernameValidator.validate(username).stream().anyMatch(
                error -> error.containsKey("error") && error.get("error").equals("invalid username")
                        && error.containsKey("message") && error.get("message").equals("user with this username already exists")
        ));

    }

    @Test
    @DisplayName("Should not give error that username already exists")
    public void ShouldNotRejectUsernameIfItPersistsInDB() {
        when(userService.findEntityByUsername(anyString())).thenThrow(new UsernameNotFoundException(""));

        Predicate<Map<String, String>> usernameInDbPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid username")
                && error.containsKey("message") && error.get("message").equals("user with this username already exists");


        assertFalse(usernameValidator.validate("whatever").stream().anyMatch(usernameInDbPredicate
        ));
    }

    @Test
    @DisplayName("Should fail if username not matches pattern")
    public void ShouldFailIfUsernameNotMatchesPattern() {
        when(userService.findEntityByUsername(anyString())).thenThrow(new UsernameNotFoundException(""));

        Predicate<Map<String, String>> patternNotMatchPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid username")
                && error.containsKey("message") && error.get("message").equals("username must be: from 3 to 16, may include lowercase, numbers, '-', '_'");

        Predicate<Map<String, String>> usernameIsNullPredicate = error -> error.containsKey("error") && error.get("error").equals("username is obligatory")
                && error.containsKey("message") && error.get("message").equals("username is obligatory");

        assertTrue(usernameValidator.validate("").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("aa").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("aaaaaaaaaaaaaaaaa").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("11").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("11111111111111111").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("--").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("-----------------").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("__").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("+++").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate("++++++++++++++++").stream().anyMatch(patternNotMatchPredicate));
        assertTrue(usernameValidator.validate(null).stream().anyMatch(usernameIsNullPredicate));

    }

    @Test
    @DisplayName("Should fail if username matches pattern but does exist in db")
    public void ShouldFailIfMatchesPatterButInDb() {
        when(userService.findEntityByUsername(anyString())).thenReturn(new UserEntity());

        Predicate<Map<String, String>> usernameInDbPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid username")
                && error.containsKey("message") && error.get("message").equals("user with this username already exists");

        assertTrue(usernameValidator.validate("aaa").stream().anyMatch(usernameInDbPredicate));
        assertTrue(usernameValidator.validate("aaaaaaaaaaaaaaaa").stream().anyMatch(usernameInDbPredicate));
        assertTrue(usernameValidator.validate("111").stream().anyMatch(usernameInDbPredicate));
        assertTrue(usernameValidator.validate("1111111111111111").stream().anyMatch(usernameInDbPredicate));
        assertTrue(usernameValidator.validate("---").stream().anyMatch(usernameInDbPredicate));
        assertTrue(usernameValidator.validate("----------------").stream().anyMatch(usernameInDbPredicate));
        assertTrue(usernameValidator.validate("___").stream().anyMatch(usernameInDbPredicate));
        assertTrue(usernameValidator.validate("________________").stream().anyMatch(usernameInDbPredicate));

    }

    @Test
    @DisplayName("Should pass if username matches pattern and does not exist in db")
    public void ShouldPassIfMatchesPatterAndNotInDb() {
        when(userService.findEntityByUsername(anyString())).thenThrow(new UsernameNotFoundException(""));

        assertTrue(usernameValidator.validate("aaa").isEmpty());
        assertTrue(usernameValidator.validate("aaaaaaaaaaaaaaaa").isEmpty());
        assertTrue(usernameValidator.validate("111").isEmpty());
        assertTrue(usernameValidator.validate("1111111111111111").isEmpty());
        assertTrue(usernameValidator.validate("---").isEmpty());
        assertTrue(usernameValidator.validate("----------------").isEmpty());
        assertTrue(usernameValidator.validate("___").isEmpty());
        assertTrue(usernameValidator.validate("________________").isEmpty());

    }

}