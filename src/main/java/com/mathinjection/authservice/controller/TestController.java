package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.AuthResponseDto;
import com.mathinjection.authservice.dto.ErrorResponseDto;
import com.mathinjection.authservice.openApi.SecuredController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController implements SecuredController {
    @GetMapping("like")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "403",description = "FORBIDDEN"),

    })
    public String like_post() {
        return "liked";
    }

    @GetMapping("comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "403",description = "FORBIDDEN"),

    })
    public String comment_post() {
        return "commented";
    }

}
