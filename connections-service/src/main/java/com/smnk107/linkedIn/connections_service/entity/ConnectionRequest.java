package com.smnk107.linkedIn.connections_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
//import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.text.DateFormat;

@Data
@Entity
public class ConnectionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long requesterUserId;
    private Long requestedUserId;
    @Enumerated(EnumType.STRING)
    //@DefaultValue(RequestStatus.PENDING)
    private String requestStatus;
    @CreationTimestamp
    private DateFormat createdTime;
}
