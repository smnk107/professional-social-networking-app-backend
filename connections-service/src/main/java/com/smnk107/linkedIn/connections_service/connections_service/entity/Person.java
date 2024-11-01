package com.smnk107.linkedIn.connections_service.connections_service.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Person {

    @Id
    @GeneratedValue
    Long Id; //node id
    Long userId;
    String name;

}
