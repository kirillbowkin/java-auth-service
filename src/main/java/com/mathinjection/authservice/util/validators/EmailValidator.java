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
public class EmailValidator implements CustomValidator<String> {

    private final String emailPattern = "(?=.{3,50}$)^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$";
    private final UserService userService;

    @Override
    public List<Map<String, String>> validate(String email) {
        if (email == null) {
            return new ArrayList<>() {{
                add(new HashMap<>() {{
                    put("error", "email is obligatory");
                    put("message", "email is obligatory");
                }});
            }};
        }

        try {
            userService.findByEmail(email);
            return new ArrayList<>() {{
                add(new HashMap<>() {{
                    put("error", "invalid email");
                    put("message", "user with this email already exists");
                }});
            }};
        } catch (UsernameNotFoundException e) {
            //TODO: Refactor
        }

        if (!Pattern.matches(emailPattern, email)) {
            return new ArrayList<>() {{
                add(new HashMap<>() {{
                    put("error", "invalid email");
                    put("message", "invalid email format");
                }});
            }};
        }

        return new ArrayList<>();
    }
}
