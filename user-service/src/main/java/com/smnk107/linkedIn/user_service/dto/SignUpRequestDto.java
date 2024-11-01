package com.smnk107.linkedIn.user_service.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {

    public String name;
    public String email;
    public String password;
}
