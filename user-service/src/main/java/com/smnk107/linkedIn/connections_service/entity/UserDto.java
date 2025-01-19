package com.smnk107.linkedIn.connections_service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    public Long id;
    public  Long userId;
    public String name;
}
