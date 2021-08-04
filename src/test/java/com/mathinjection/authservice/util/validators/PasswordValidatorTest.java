package com.mathinjection.authservice.util.validators;

import com.mathinjection.authservice.configuration.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Map;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@DisplayName("Password Validator")
class PasswordValidatorTest {

    @Autowired
    private PasswordValidator passwordValidator;

    @Test
    @DisplayName("Should give an error if password is not valid")
    public void shouldGiveErrorIfPasswordNotValid() {

        Predicate<Map<String, String>> patternDontMatchPredicate = error -> error.containsKey("error") && error.get("error").equals("invalid password")
                && error.containsKey("message") && error.get("message").equals("password must be: from 8 to 16, at least one letter, at least one number, available special characters: '@$!%*#?&'");

        Predicate<Map<String, String>> passwordNullPredicate = error -> error.containsKey("error") && error.get("error").equals("password is obligatory")
                && error.containsKey("message") && error.get("message").equals("password is obligatory");



        assertTrue(passwordValidator.validate("").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("aaaaaaa").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("1111111").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("@@@@@@@").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("$$$$$$$").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("!!!!!!!").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("%%%%%%%").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("*******").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("#######").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("???????").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("&&&&&&&").stream().anyMatch(patternDontMatchPredicate));

        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaaaaa").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("11111111111111111").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("@@@@@@@@@@@@@@@@@").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("$$$$$$$$$$$$$$$$$").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("!!!!!!!!!!!!!!!!!").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("%%%%%%%%%%%%%%%%%").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("*****************").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("#################").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("?????????????????").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("&&&&&&&&&&&&&&&&&").stream().anyMatch(patternDontMatchPredicate));

        assertTrue(passwordValidator.validate("aaaaaaaa").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("11111111").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("@@@@@@@@").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("$$$$$$$$").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("!!!!!!!!").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("%%%%%%%%").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("********").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("########").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("????????").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("&&&&&&&&").stream().anyMatch(patternDontMatchPredicate));

        assertTrue(passwordValidator.validate("@a@@@@@@@").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("$a$$$$$$$").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("!a!!!!!!!").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("%a%%%%%%%").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("*a*******").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("#a#######").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("?a???????").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("&a&&&&&&&").stream().anyMatch(patternDontMatchPredicate));

        assertTrue(passwordValidator.validate("@1@@@@@@@").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("$1$$$$$$$").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("!1!!!!!!!").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("%1%%%%%%%").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("*1*******").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("#1#######").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("?1???????").stream().anyMatch(patternDontMatchPredicate));
        assertTrue(passwordValidator.validate("&1&&&&&&&").stream().anyMatch(patternDontMatchPredicate));

        assertTrue(passwordValidator.validate(null).stream().anyMatch(passwordNullPredicate));
    }

    @Test
    @DisplayName("Should not give errors if password is valid")
    public void shouldNotGiveErrorIfPasswordValid() {
        //@$!%*#?&
        assertTrue(passwordValidator.validate("aaaaaaa1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaa@1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaa$1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaa!1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaa%1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaa*1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaa#1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaa?1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaa&1").isEmpty());

        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaaa1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaa@1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaa$1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaa!1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaa%1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaa*1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaa#1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaa?1").isEmpty());
        assertTrue(passwordValidator.validate("aaaaaaaaaaaaaa&1").isEmpty());
    }
}