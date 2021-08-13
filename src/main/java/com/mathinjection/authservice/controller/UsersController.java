package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.*;
import com.mathinjection.authservice.dto.ResponseStatus;
import com.mathinjection.authservice.entity.UserEntity;
import com.mathinjection.authservice.model.UserModel;
import com.mathinjection.authservice.openApi.SecuredController;
import com.mathinjection.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/v1/")
public class UsersController implements SecuredController {

    private final UserService userService;

    @GetMapping("all")
    public ResponseEntity<? extends BaseResponseDto> getUsers() {
        try {
            List<UserModel> userModels = userService.findAll();
            return ResponseEntity
                    .ok()
                    .body(
                            new GetUsersResponseDto()
                                    .setUsers(userModels)
                                    .setPath("/users")
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting users");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath("/users")
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }

    }

    @GetMapping("{username}")
    public ResponseEntity<? extends BaseResponseDto> getUserByUsername(@PathVariable(name = "username") String username) {
        try {
            UserModel userModel = userService.findModelByUsername(username);
            return ResponseEntity
                    .ok()
                    .body(
                            new GetUserResponseDto()
                                    .setUser(userModel)
                                    .setPath(new StringBuilder().append("/user/").append(username).toString())
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (UsernameNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting user");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath(new StringBuilder().append("/user/").append(username).toString())
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @GetMapping
    public ResponseEntity<? extends BaseResponseDto> getUserById(@RequestParam(name = "id") UUID id) {
        try {
            UserModel userModel = userService.findModelById(id);
            return ResponseEntity
                    .ok()
                    .body(
                            new GetUserResponseDto()
                                    .setUser(userModel)
                                    .setPath(new StringBuilder().append("/user/").append(id).toString())
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (UsernameNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting user");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath(new StringBuilder().append("/user/").append(id).toString())
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }
}
