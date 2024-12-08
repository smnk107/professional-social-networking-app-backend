package com.smnk107.linkedIn.posts_service.clientEntities;

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
