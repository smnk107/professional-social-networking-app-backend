package com.smnk107.linkedIn.connections_service.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@Data
@Builder
public class UserDto {
    @Id
    @GeneratedValue
    public Long id;
    public  Long userId;
    public String name;
}
