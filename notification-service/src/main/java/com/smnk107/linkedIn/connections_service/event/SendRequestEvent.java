package com.smnk107.linkedIn.connections_service.event;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class SendRequestEvent {

    Long senderId;
    Long ReceiverId;

    LocalDateTime createdTime;
}
