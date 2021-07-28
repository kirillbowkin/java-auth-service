package com.mathinjection.authservice.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseStatus {
    SUCCESS("Success"),
    ERROR("Error");

    private String status;


}
