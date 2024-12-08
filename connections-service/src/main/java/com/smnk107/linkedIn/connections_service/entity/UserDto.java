package com.smnk107.linkedIn.connections_service.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@Data
public class UserDto {
    @Id
    @GeneratedValue
    public Long id;
    public  Long userId;
    public String name;
}
