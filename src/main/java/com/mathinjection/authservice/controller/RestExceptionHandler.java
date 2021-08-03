package com.mathinjection.authservice.controller;

import com.mathinjection.authservice.dto.ErrorResponseDto;
import com.mathinjection.authservice.dto.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(
                        new ErrorResponseDto()
                                .setErrors(new ArrayList<>() {{
                                    add(new HashMap<>() {{
                                        put("error", "empty body");
                                        put("message", "body is not provided");
                                    }});
                                }})
                                .setStatus(ResponseStatus.ERROR)
                                .setPath(request.getDescription(false).substring(4))
                                .setTimestamp(LocalDateTime.now())
                );
    }
}

