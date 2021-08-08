package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.*;
import com.mathinjection.authservice.openApi.SecuredController;
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

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController implements SecuredController {

    private final UserService userService;

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
        return ResponseEntity
                .ok()
                .body(
                        new GetUsersResponseDto()
                                .setUsers(userService.findAll())
                                .setPath("/users")
                                .setStatus(ResponseStatus.SUCCESS)
                                .setTimestamp(LocalDateTime.now())
                );
    }

}
