package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.ResponseStatus;
import com.mathinjection.authservice.dto.*;
import com.mathinjection.authservice.model.FlatAuthorityModel;
import com.mathinjection.authservice.openApi.SecuredController;
import com.mathinjection.authservice.service.AuthorityService;
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
@RequestMapping("/api/authorities/v1")
@RequiredArgsConstructor
public class AuthorityController implements SecuredController {
    private final AuthorityService authorityService;

    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('GET_AUTHORITIES')")
    public ResponseEntity<? extends BaseResponseDto> getAuthorities() {
        try {
            List<FlatAuthorityModel> authorityModels = authorityService.getAll();

            return ResponseEntity
                    .ok()
                    .body(
                            new GetAuthoritiesDto()
                                    .setAuthorities(authorityModels)
                                    .setPath("/api/authorities/v1")
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting authorities");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath("/api/authorities/v1")
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @GetMapping("{authorityName}")
    @PreAuthorize("hasAnyAuthority('GET_AUTHORITIES')")
    public ResponseEntity<? extends BaseResponseDto> getAuthorityByName(@PathVariable("authorityName") String authorityName) {
        try {
            FlatAuthorityModel authorityModel = authorityService.findAuthorityByName(authorityName);

            return ResponseEntity
                    .ok()
                    .body(
                            new GetAuthorityDto()
                                    .setAuthority(authorityModel)
                                    .setPath("/api/authorities/v1")
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting authority");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath(new StringBuilder().append("/api/authorities/v1/").append(authorityName).toString())
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('GET_AUTHORITIES')")
    public ResponseEntity<? extends BaseResponseDto> getAuthorityById(@RequestParam("id")UUID authorityId) {
        try {
            FlatAuthorityModel authorityModel = authorityService.findAuthorityById(authorityId);

            return ResponseEntity
                    .ok()
                    .body(
                            new GetAuthorityDto()
                                    .setAuthority(authorityModel)
                                    .setPath("/api/authorities/v1")
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while getting authority");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath(new StringBuilder().append("/api/authorities/v1?id=").append(authorityId).toString())
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @PostMapping("addAuthority")
    @PreAuthorize("hasAnyAuthority('ADD_AUTHORITIES')")
    public ResponseEntity<? extends BaseResponseDto> addAuthority(@RequestBody AddAuthorityDto addAuthorityDto) {
        try {

//            TODO: Add validation on authorityName
            FlatAuthorityModel authorityModel = authorityService.addAuthority(addAuthorityDto.getAuthorityName());

            return ResponseEntity
                    .ok()
                    .body(
                            new GetAuthorityDto()
                                    .setAuthority(authorityModel)
                                    .setPath("/api/authorities/v1")
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while adding authority");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath("/api/authorities/v1/addAuthority")
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }

    @DeleteMapping("deleteAuthority/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_AUTHORITIES')")
    public ResponseEntity<? extends BaseResponseDto> deleteAuthority(@PathVariable("id") UUID authorityId) {
        try {
            authorityService.deleteAuthorityById(authorityId);

            return ResponseEntity
                    .ok()
                    .body(
                            new SuccessRespDto()
                                    .setPath("/api/authorities/v1/deleteAuthority/" + authorityId)
                                    .setStatus(ResponseStatus.SUCCESS)
                                    .setTimestamp(LocalDateTime.now())
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ErrorResponseDto()
                                    .setErrors(Collections.singletonList(new HashMap<>() {{
                                        put("error", "error while deleting authority");
                                        put("message", e.getMessage());
                                    }}))
                                    .setPath("/api/authorities/v1/deleteAuthority/" + authorityId)
                                    .setStatus(ResponseStatus.ERROR)
                                    .setTimestamp(LocalDateTime.now())
                    );
        }
    }
































}
