package com.smnk107.linkedIn.connections_service.event;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Data
public class SendRequestEvent {

    Long senderId;
    Long ReceiverId;
    @CreationTimestamp
    LocalDateTime createdTime;
}
