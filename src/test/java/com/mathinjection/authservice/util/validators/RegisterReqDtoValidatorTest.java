package com.mathinjection.authservice.util.validators;

import com.mathinjection.authservice.configuration.TestConfig;
import com.mathinjection.authservice.dto.RegisterReqDto;
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
@DisplayName("Register Request Dto Validator")
class RegisterReqDtoValidatorTest {

    @Autowired
    private RegisterReqDtoValidator registerReqDtoValidator;

    @Autowired
    private UserService userService;

    @AfterEach
    public void mockito_reset() {
        Mockito.reset(userService);
    }

    @Test
    @DisplayName("Should fail if RegisterRequestDto is invalid because of pattern mismatch")
    public void shouldFailIFRegisterRequestDtoInvalidBecausePatternMismatch() {
        when(userService.findEntityByUsername(anyString())).thenThrow(new UsernameNotFoundException(""));
        when(userService.findEntityByEmail(anyString())).thenThrow(new UsernameNotFoundException(""));

        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto(null, null, null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto(null, null, "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto(null, "", null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto(null, "", "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("", null, null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("", null, "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("", "", null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("", "", "")).isEmpty());

        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("aa", null, null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("aaaaaaaaaaaaaaaaa", null, "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("11", "", null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("11111111111111111", "", "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("--", null, null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("-----------------", null, "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("__", "", null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("+++", "", "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("++++++++++++++++", "", "")).isEmpty());

        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("aa", "rewrew@.com", null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("aaaaaaaaaaaaaaaaa", "43243@.com", "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("11", "......@.com", null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("11111111111111111", "%%%%%%%@.com", "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("--", "-------@.com", null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("-----------------", "________@.com", "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("__", "rewrew.fdsjkl", null)).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("+++", "fjsdk@gmail.comjjkljkjljljlkjlfffffffffffffffffffff", "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("++++++++++++++++", "", "")).isEmpty());

        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("aa", "rewrew@.com", "aaaaaaa%1")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("aaaaaaaaaaaaaaaaa", "43243@.com", "aaaaaaa*1")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("11", "......@.com", "aaaaaaa#1")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("11111111111111111", "%%%%%%%@.com", "aaaaaaa?1")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("--", "-------@.com", "aaaaaaaaaaaaaaa1")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("-----------------", "________@.com", "aaaaaaaaaaaaaa@1")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("__", "rewrew.fdsjkl", "aaaaaaaaaaaaaa$1")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("+++", "fjsdk@gmail.comjjkljkjljljlkjlfffffffffffffffffffff", "")).isEmpty());
        assertFalse(registerReqDtoValidator.validate(new RegisterReqDto("++++++++++++++++", "", "aaaaaaaaaaaaaa!1")).isEmpty());
    }

    @Test
    @DisplayName("Should pass if RegisterRequestDto pattern matches")
    public void shouldPassIfRegisterRequestDtoPatterMatches() {
        when(userService.findEntityByUsername(anyString())).thenThrow(new UsernameNotFoundException(""));
        when(userService.findEntityByEmail(anyString())).thenThrow(new UsernameNotFoundException(""));

        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("aaa", "fassfd@fjf.fddd", "aaaaaaa%1")).isEmpty());
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("aaaaaaaaaaaaaaaa", "test@gogle.com", "aaaaaaa*1")).isEmpty());
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("111", "43sfd@fjf.fddd", "aaaaaaa#1")).isEmpty());
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("1111111111111111", "fsfd_@jf.fddd", "aaaaaaa?1")).isEmpty());
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("---", "fsfd%@jf.fddd", "aaaaaaaaaaaaaaa1")).isEmpty());
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("----------------", "fsfd-@jf.fddd", "aaaaaaaaaaaaaa@1")).isEmpty());
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("___", "fsfd.@jf.fddd", "aaaaaaaaaaaaaa$1")).isEmpty());
    }

    @Test
    @DisplayName("Should fail if username exists in db")
    public void shouldFailIfUsernameInDb() {
        when(userService.findEntityByUsername(anyString())).thenReturn(new UserEntity());
        when(userService.findEntityByEmail(anyString())).thenThrow(new UsernameNotFoundException(""));

        Predicate<Map<String, String>> usernameInDbPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid username")
                && error.containsKey("message") && error.get("message").equals("user with this username already exists");

        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("aaa", "fassfd@fjf.fddd", "aaaaaaa%1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("aaaaaaaaaaaaaaaa", "test@gogle.com", "aaaaaaa*1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("111", "43sfd@fjf.fddd", "aaaaaaa#1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("1111111111111111", "fsfd_@jf.fddd", "aaaaaaa?1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("---", "fsfd%@jf.fddd", "aaaaaaaaaaaaaaa1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("----------------", "fsfd-@jf.fddd", "aaaaaaaaaaaaaa@1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("___", "fsfd.@jf.fddd", "aaaaaaaaaaaaaa$1")).stream().anyMatch(usernameInDbPredicate));

    }

    @Test
    @DisplayName("Should fail if email exists in db")
    public void shouldFailIfEmailInDb() {
        when(userService.findEntityByUsername(anyString())).thenThrow(new UsernameNotFoundException(""));
        when(userService.findEntityByEmail(anyString())).thenReturn(new UserEntity());

        Predicate<Map<String, String>> usernameInDbPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid email")
                && error.containsKey("message") && error.get("message").equals("user with this email already exists");

        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("aaa", "fassfd@fjf.fddd", "aaaaaaa%1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("aaaaaaaaaaaaaaaa", "test@gogle.com", "aaaaaaa*1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("111", "43sfd@fjf.fddd", "aaaaaaa#1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("1111111111111111", "fsfd_@jf.fddd", "aaaaaaa?1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("---", "fsfd%@jf.fddd", "aaaaaaaaaaaaaaa1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("----------------", "fsfd-@jf.fddd", "aaaaaaaaaaaaaa@1")).stream().anyMatch(usernameInDbPredicate));
        assertTrue(registerReqDtoValidator.validate(new RegisterReqDto("___", "fsfd.@jf.fddd", "aaaaaaaaaaaaaa$1")).stream().anyMatch(usernameInDbPredicate));

    }
}