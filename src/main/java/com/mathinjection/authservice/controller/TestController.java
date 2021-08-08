package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.*;
import com.mathinjection.authservice.entity.UserEntity;
import com.mathinjection.authservice.model.RoleModel;
import com.mathinjection.authservice.model.UserModel;
import com.mathinjection.authservice.openApi.SecuredController;
import com.mathinjection.authservice.repository.AuthorityRepository;
import com.mathinjection.authservice.repository.RoleRepository;
import com.mathinjection.authservice.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController implements SecuredController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @GetMapping("like")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),

    })
    public String like_post() {
        return "liked";
    }

    @GetMapping("comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),

    })
    public String comment_post() {
        return "commented";
    }

    @GetMapping("users")
    public ResponseEntity<? extends BaseResponseDto> getUsers() {
        List<UserEntity> userEntities = userService.findAll();
        List<UserModel> userModels = userEntities.stream().map(UserModel::EntityToModel).collect(Collectors.toList());
        return ResponseEntity
                .ok()
                .body(
                        new GetUsersResponseDto()
                                .setUsers(userModels)
                                .setPath("/users")
                                .setStatus(ResponseStatus.SUCCESS)
                                .setTimestamp(LocalDateTime.now())
                );
    }

    @GetMapping("roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok().body((roleRepository.findAll().stream().map(RoleModel::EntityToModel)));
    }

    @GetMapping("authorities")
    public ResponseEntity<?> getAuthorities() {
        return ResponseEntity.ok().body(authorityRepository.findAll());
    }

}
