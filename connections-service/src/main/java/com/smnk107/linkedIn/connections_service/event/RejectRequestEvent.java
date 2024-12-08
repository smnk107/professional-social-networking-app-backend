package com.smnk107.linkedIn.connections_service.event;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class RejectRequestEvent {

    Long senderId;
    Long rejectorId;
    @CreationTimestamp
    LocalDateTime createdAt;
}
