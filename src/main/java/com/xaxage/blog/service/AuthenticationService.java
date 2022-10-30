package com.xaxage.blog.service;

import com.xaxage.blog.payload.JwtAuthResponse;
import com.xaxage.blog.payload.LoginDto;
import com.xaxage.blog.payload.RegisterDto;

public interface AuthenticationService {

    JwtAuthResponse loginUser(LoginDto loginDto);

    void registerUser(RegisterDto registerDto);

}
