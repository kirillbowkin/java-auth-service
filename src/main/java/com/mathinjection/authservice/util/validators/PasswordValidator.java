package com.mathinjection.authservice.util.validators;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class PasswordValidator implements CustomValidator<String> {

    private final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,16}$";

    @Override
    public List<Map<String, String>> validate(String password) {
        if (password == null) {
            return new ArrayList<>(){{
                add(new HashMap<>(){{
                    put("error", "password is obligatory");
                    put("message", "password is obligatory");
                }});
            }};
        }

        if (!Pattern.matches(passwordPattern, password)) {
            return new ArrayList<>(){{
                add(new HashMap<>() {{
                    put("error", "invalid password");
                    put("message", "password must be: from 8 to 16, at least one letter, at least one number, available special characters: '@$!%*#?&'");
                }});
            }};
        }

        return new ArrayList<>();
    }
}
