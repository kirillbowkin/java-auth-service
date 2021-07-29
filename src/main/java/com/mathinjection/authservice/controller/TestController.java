package com.mathinjection.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("like")
    public String like_post() {
        return "liked";
    }

    @GetMapping("comment")
    public String comment_post() {
        return "commented";
    }

}
