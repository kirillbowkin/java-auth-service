package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.*;
import com.mathinjection.authservice.dto.ResponseStatus;
import com.mathinjection.authservice.model.UserModel;
import com.mathinjection.authservice.openApi.SecuredController;
import com.mathinjection.authservice.repository.RoleRepository;
import com.mathinjection.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/v1/")
public class UsersController implements SecuredController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('GET_USERS')")
    @GetMapping("all")
    public ResponseEntity<? extends BaseResponseDto> getUsers() {
        try {
            List<UserModel> userModels = userService.findAll();
            return ResponseEntity
                    .ok()
                    .body(
                            new GetUsersResponseDto()
                                    .setUsers(userModels)
                                    .setPath("/api/users/v1")
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
                                    .setPath("/api/users/v1/users")
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }

    }

    @GetMapping("{username}")
    @PreAuthorize("hasAnyAuthority('GET_USERS')")
    public ResponseEntity<? extends BaseResponseDto> getUserByUsername(@PathVariable(name = "username") String username) {
        try {
            UserModel userModel = userService.findModelByUsername(username);
            return ResponseEntity
                    .ok()
                    .body(
                            new GetUserResponseDto()
                                    .setUser(userModel)
                                    .setPath(new StringBuilder().append("/api/users/v1/").append(username).toString())
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
                                    .setPath(new StringBuilder().append("/api/users/v1/").append(username).toString())
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('GET_USERS')")
    public ResponseEntity<? extends BaseResponseDto> getUserById(@RequestParam(name = "id") UUID id) {
        try {
            UserModel userModel = userService.findModelById(id);
            return ResponseEntity
                    .ok()
                    .body(
                            new GetUserResponseDto()
                                    .setUser(userModel)
                                    .setPath(new StringBuilder().append("/api/users/v1?id='").append(id).append("'").toString())
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
                                    .setPath(new StringBuilder().append("/api/users/v1?id='").append(id).append("'").toString())
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @PostMapping("addRole")
    @PreAuthorize("hasAnyAuthority('EDIT_USERS')")
    public ResponseEntity<? extends BaseResponseDto> addRoleToUser(@RequestBody UserRoleDto userRoleDto) {
        try {
            userService.addRoleToUser(userRoleDto.getUserId(), userRoleDto.getRoleId());
            return ResponseEntity.ok().body(
                    new SuccessRespDto()
                            .setPath("/api/users/v1/addRole")
                            .setStatus(ResponseStatus.SUCCESS)
                            .setTimestamp(LocalDateTime.now())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponseDto()
                            .setErrors(Collections.singletonList(new HashMap<>() {{
                        put("error", "error while adding role to user");
                        put("message", e.getMessage());
                    }}))
                    .setPath("/api/users/v1/addRole")
                    .setStatus(ResponseStatus.ERROR)
                    .setTimestamp(LocalDateTime.now())
            );
        }
    }

    @DeleteMapping("deleteRole")
    @PreAuthorize("hasAnyAuthority('EDIT_USERS')")
    public ResponseEntity<? extends BaseResponseDto> deleteRoleFromUser(@RequestBody UserRoleDto deleteRoleDto) {
        try {
            userService.deleteRoleFromUser(deleteRoleDto.getUserId(), deleteRoleDto.getRoleId());
            return ResponseEntity.ok().body(
                    new SuccessRespDto()
                            .setPath("/api/users/v1/addRole")
                            .setStatus(ResponseStatus.SUCCESS)
                            .setTimestamp(LocalDateTime.now())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponseDto()
                            .setErrors(Collections.singletonList(new HashMap<>() {{
                                put("error", "error while adding role to user");
                                put("message", e.getMessage());
                            }}))
                            .setPath("/api/users/v1/addRole")
                            .setStatus(ResponseStatus.ERROR)
                            .setTimestamp(LocalDateTime.now())
            );
        }
    }
}
