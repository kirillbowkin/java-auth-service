package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.RegisterReqDto;
import com.mathinjection.authservice.model.Role;
import com.mathinjection.authservice.model.User;
import com.mathinjection.authservice.repository.RoleRepository;
import com.mathinjection.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/auth/v1/")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto regReqDto) {
        try {
            Role role = roleRepository.findRoleByName("ROLE_USER").orElse(null);
            User savedUser = userService.save(
                    new User()
                            .setUsername(regReqDto.getUsername())
                            .setPassword(passwordEncoder.encode(regReqDto.getPassword()))
                            .setEmail(regReqDto.getEmail())
                            .setRoles(Collections.singletonList(role))
            );

            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            System.out.println(e);
        }

        return (ResponseEntity<?>) ResponseEntity.badRequest();
    }
}
