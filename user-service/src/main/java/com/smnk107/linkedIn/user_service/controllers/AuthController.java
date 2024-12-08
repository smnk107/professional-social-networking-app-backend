package com.smnk107.linkedIn.user_service.controllers;

import com.smnk107.linkedIn.user_service.dto.LogInDto;
import com.smnk107.linkedIn.user_service.dto.SignUpRequestDto;
import com.smnk107.linkedIn.user_service.dto.UserDto;
import com.smnk107.linkedIn.user_service.entity.User;
import com.smnk107.linkedIn.user_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @GetMapping("/getAllUser")
    ResponseEntity<List<UserDto>> getAllUser()
    {
        List<UserDto> list = authService.getAllUser()
                .getBody()
                .stream()
                .map(elem->modelMapper.map(elem,UserDto.class))
                .collect(Collectors.toList());

        return  ResponseEntity.ok(list);
    }
    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        return authService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    ResponseEntity<String> logIn(@RequestBody LogInDto logInDto)
    {
        return authService.login(logInDto);
    }
}
