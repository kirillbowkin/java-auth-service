package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.*;
import com.mathinjection.authservice.dto.ResponseStatus;
import com.mathinjection.authservice.model.FlatRoleModel;
import com.mathinjection.authservice.model.RoleModel;
import com.mathinjection.authservice.openApi.SecuredController;
import com.mathinjection.authservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles/v1/")
public class RolesController implements SecuredController {
    private final RoleService roleService;

    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('GET_ROLES')")
    public ResponseEntity<? extends BaseResponseDto> getRoles() {
        try {
            List<RoleModel> roleModels = roleService.findAll();
            return ResponseEntity
                    .ok()
                    .body(
                            new GetRolesResponseDto()
                                    .setRoles(roleModels)
                                    .setPath("/api/roles/v1/all")
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting roles");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath("/api/roles/v1")
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @GetMapping("{roleName}")
    @PreAuthorize("hasAnyAuthority('GET_ROLES')")
    public ResponseEntity<? extends BaseResponseDto> getRoleByRoleName(@PathVariable("roleName") String roleName) {
        try {
            RoleModel roleModel = roleService.findModelByRoleName(roleName);
            return ResponseEntity
                    .ok()
                    .body(
                            new GetRoleResponseDto()
                                    .setRole(roleModel)
                                    .setPath(new StringBuilder().append("/api/roles/v1/").append(roleName).toString())
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting role");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath(new StringBuilder().append("/api/roles/v1/").append(roleName).toString())
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('GET_ROLES')")
    public ResponseEntity<? extends BaseResponseDto> getRoleByRoleId(@RequestParam("roleId") UUID roleId) {
        try {
            RoleModel roleModel = roleService.findModelById(roleId);
            return ResponseEntity
                    .ok()
                    .body(
                            new GetRoleResponseDto()
                                    .setRole(roleModel)
                                    .setPath(new StringBuilder().append("/api/roles/v1?id='").append(roleId).append("'").toString())
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting role");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath(new StringBuilder().append("/api/roles/v1?id='").append(roleId).append("'").toString())
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @PostMapping("addAuthority")
    @PreAuthorize("hasAnyAuthority('EDIT_ROLES')")
    public ResponseEntity<? extends BaseResponseDto> addAuthorityToRole(@RequestBody RoleAuthorityDto roleAuthorityDto) {
        try {
            roleService.addAuthorityToRole(roleAuthorityDto.getRoleId(), roleAuthorityDto.getAuthorityId());
            return ResponseEntity
                    .ok()
                    .body(
                            new SuccessRespDto()
                                    .setPath("/api/roles/v1/addAuthority")
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while adding authority to role");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath("/api/roles/v1/addAuthority")
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @DeleteMapping("deleteAuthority")
    @PreAuthorize("hasAnyAuthority('EDIT_ROLES')")
    public ResponseEntity<? extends BaseResponseDto> deleteAuthorityFromRole(@RequestBody RoleAuthorityDto roleAuthorityDto) {
        try {
            roleService.deleteAuthorityFromRole(roleAuthorityDto.getRoleId(), roleAuthorityDto.getAuthorityId());
            return ResponseEntity
                    .ok()
                    .body(
                            new SuccessRespDto()
                                    .setPath("/api/roles/v1/addAuthority")
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while deleting authority from role");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath("/api/roles/v1/addAuthority")
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }


}
