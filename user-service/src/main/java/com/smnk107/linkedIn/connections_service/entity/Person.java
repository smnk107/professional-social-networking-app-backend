package com.smnk107.linkedIn.connections_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
public class Person {

    private Long id; //node id
    private Long userId;
    private String name;

}
