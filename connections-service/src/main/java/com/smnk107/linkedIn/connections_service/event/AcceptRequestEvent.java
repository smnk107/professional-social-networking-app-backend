package com.smnk107.linkedIn.connections_service.event;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Data
public class AcceptRequestEvent {

    Long senderId;
    Long acceptorId;
    @CreationTimestamp
    LocalDateTime createdAt;
}
