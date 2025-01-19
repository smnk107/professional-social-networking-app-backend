package com.smnk107.linkedIn.user_service.services;

import com.smnk107.linkedIn.user_service.clients.ConnectionClient;
import com.smnk107.linkedIn.user_service.dto.LogInDto;
import com.smnk107.linkedIn.user_service.dto.SignUpRequestDto;
import com.smnk107.linkedIn.user_service.dto.UserDto;
import com.smnk107.linkedIn.user_service.entity.User;
import com.smnk107.linkedIn.user_service.exceptions.BadRequestException;
import com.smnk107.linkedIn.user_service.repository.UserRepository;
import com.smnk107.linkedIn.user_service.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordUtil passwordUtil;
    private final JwtService jwtService;
    private final ConnectionClient connectionClient;

    @Transactional
    public ResponseEntity<UserDto> signUp(SignUpRequestDto signUpRequestDto)
    {
        String email = signUpRequestDto.getEmail();
        String password = signUpRequestDto.getPassword();

        boolean isPresent = userRepository.existsByEmail(email);
        if(isPresent) throw new BadRequestException("User already present with email id :"+email);

        User user = modelMapper.map(signUpRequestDto,User.class);
        user.setPassword(passwordUtil.hashPassword(password));

        User savedUser = userRepository.save(user);

        com.smnk107.linkedIn.connections_service.entity.UserDto
                userDto = com.smnk107.linkedIn.connections_service.entity.UserDto.builder()
                .userId(savedUser.getId())
                .name(savedUser.getName())
                .build();

        connectionClient.createNewPerson(userDto);

        userDto.setUserId(savedUser.getId());

        return ResponseEntity.ok(modelMapper.map(savedUser,UserDto.class));
    }

    public ResponseEntity<String> login(LogInDto logInDto)
    {
        String email = logInDto.getEmail();
        String password = logInDto.getPassword();

        boolean isPresent = userRepository.existsByEmail(email);
        if(!isPresent) throw new BadRequestException("User not present with email id :"+email);

        User user = userRepository.findByEmail(email);

        String correctPassword = user.getPassword();

        boolean isMatched = passwordUtil.matchPassword(correctPassword,password);
        if(!isMatched)
            throw new BadRequestException("Incorrect password entered");

        return ResponseEntity.ok(jwtService.createAccessToken(user));
    }

    public ResponseEntity<List<User>> getAllUser() {
        List<User> list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }
}
