package com.sachin.social_backend.controller;

import com.sachin.social_backend.dto.LoginRequest;
import com.sachin.social_backend.dto.RegisterRequest;
import com.sachin.social_backend.entity.User;
import com.sachin.social_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest request){
        return userService.register(request);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return userService.login(request);
    }
}
