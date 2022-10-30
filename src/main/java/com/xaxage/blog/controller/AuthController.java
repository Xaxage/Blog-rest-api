package com.xaxage.blog.controller;

import com.xaxage.blog.payload.JwtAuthResponse;
import com.xaxage.blog.payload.LoginDto;
import com.xaxage.blog.payload.RegisterDto;
import com.xaxage.blog.security.JwtTokenProvider;
import com.xaxage.blog.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto loginDto) {

        JwtAuthResponse jwtAuthResponse = authenticationService.loginUser(loginDto);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {

        authenticationService.registerUser(registerDto);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

}

