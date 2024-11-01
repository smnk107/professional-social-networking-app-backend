package com.smnk107.linkedIn.user_service.controllers;

import com.smnk107.linkedIn.user_service.dto.LogInDto;
import com.smnk107.linkedIn.user_service.dto.SignUpRequestDto;
import com.smnk107.linkedIn.user_service.dto.UserDto;
import com.smnk107.linkedIn.user_service.entity.User;
import com.smnk107.linkedIn.user_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/getAllUser")
    String getAllUser()
    {

        return "Hello boss !!";
    }
    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        return authService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    ResponseEntity<String> signUp(@RequestBody LogInDto logInDto)
    {
        return authService.login(logInDto);
    }
}
