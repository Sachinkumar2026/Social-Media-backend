package com.sachin.social_backend.controller;

import com.sachin.social_backend.dto.LoginRequest;
import com.sachin.social_backend.dto.RegisterRequest;
import com.sachin.social_backend.entity.User;
import com.sachin.social_backend.service.TokenBlacklistService;
import com.sachin.social_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest request){
        return userService.register(request);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);

            long expiration = 86400000;
            tokenBlacklistService.blacklistToken(token,expiration);

            return "Logged out successfully";
        }
        return "No token provided";
    }
}
