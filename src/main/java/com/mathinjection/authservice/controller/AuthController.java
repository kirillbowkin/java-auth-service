package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.*;
import com.mathinjection.authservice.model.Role;
import com.mathinjection.authservice.model.User;
import com.mathinjection.authservice.repository.RoleRepository;
import com.mathinjection.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/auth/v1/")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("register")
    public ResponseEntity<? extends BaseResponseDto> register(@RequestBody RegisterReqDto regReqDto) {
        try {
            Role role = roleRepository.findRoleByName("ROLE_USER").orElse(null);
            User savedUser = userService.save(
                    new User()
                            .setUsername(regReqDto.getUsername())
                            .setPassword(passwordEncoder.encode(regReqDto.getPassword()))
                            .setEmail(regReqDto.getEmail())
                            .setRoles(Collections.singletonList(role))
            );

            return new ResponseEntity<>(
                    new RegisterResponseDto()
                            .setUser(savedUser)
                            .setStatus(ResponseStatus.SUCCESS)
                            .setPath("/api/auth/v1/register")
                            .setTimestamp(LocalDateTime.now())
                    , HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ErrorResponseDto()
                            .setErrors(new ArrayList<>() {{
                                add(new HashMap<>() {{
                                    put("Код", "228");
                                    put("Сообщение", "Ты хуй");
                                }});
                            }})
                            .setStatus(ResponseStatus.ERROR)
                            .setPath("/api/auth/v1/register")
                            .setTimestamp(LocalDateTime.now())
                    , HttpStatus.BAD_REQUEST
            );
        }

    }
}
