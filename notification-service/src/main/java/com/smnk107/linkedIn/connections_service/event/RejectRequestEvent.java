package com.smnk107.linkedIn.connections_service.event;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class RejectRequestEvent {

    Long senderId;
    Long rejectorId;

    LocalDateTime createdAt;
}
