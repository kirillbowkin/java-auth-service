package com.mathinjection.authservice.util.validators;

import com.mathinjection.authservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UsernameValidator implements CustomValidator<String> {

    private final String usernamePattern = "^[a-z0-9_-]{3,16}$";
    private final UserService userService;

    @Override
    public List<Map<String, String>> validate(String username) {
        if (username == null) {
            return new ArrayList<>(){{
                add(new HashMap<>(){{
                    put("error", "username is obligatory");
                    put("message", "username is obligatory");
                }});
            }};
        }

        try {
            userService.findByUsername(username);
            return new ArrayList<>(){{
                add(new HashMap<>(){{
                    put("error", "invalid username");
                    put("message", "user with this username already exists");
                }});
            }};
        } catch (UsernameNotFoundException e) {
            //TODO: Refactor
        }

        if (!Pattern.matches(usernamePattern, username)) {
            return new ArrayList<>(){{
                add(new HashMap<>(){{
                    put("error", "invalid username");
                    put("message", "username must be: from 3 to 16, may include lowercase, numbers, '-', '_'");
                }});
            }};
        }

        return new ArrayList<>();
    }
}
