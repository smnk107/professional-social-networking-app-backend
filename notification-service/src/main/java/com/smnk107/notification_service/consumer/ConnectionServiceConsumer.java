package com.smnk107.notification_service.consumer;

import com.smnk107.linkedIn.connections_service.event.AcceptRequestEvent;
import com.smnk107.linkedIn.connections_service.event.RejectRequestEvent;
import com.smnk107.linkedIn.connections_service.event.SendRequestEvent;
import com.smnk107.notification_service.entity.Notification;
import com.smnk107.notification_service.repository.NotificationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Data
@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionServiceConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "request-sent-topic")
    void requestSentTopicHandler(SendRequestEvent sendRequestEvent)
    {
        Long sender = sendRequestEvent.getSenderId();
        Long receiver = sendRequestEvent.getReceiverId();
        String message = String.format("user with id: %d has sent connection request to you",sender);
        sendNotification(sender,receiver,message);
    }

    @KafkaListener(topics = "request-accepted-topic")
    void requestAcceptedTopicHandler(AcceptRequestEvent acceptRequestEvent)
    {
        Long sender = acceptRequestEvent.getAcceptorId();
        Long receiver = acceptRequestEvent.getSenderId();
        String message = String.format("user with id %d has accepted your connection request",sender);
        sendNotification(sender,receiver,message);
    }

    @KafkaListener(topics = "request-rejected-topic")
    void requestRejectedTopicHandler(RejectRequestEvent rejectRequestEvent)
    {
        Long sender = rejectRequestEvent.getRejectorId();
        Long receiver = rejectRequestEvent.getSenderId();
        String message = String.format("user with id %d has rejected your connection request",sender);
        sendNotification(sender,receiver,message);
    }




    void sendNotification(Long receiverId , Long senderId, String message)
    {
        Notification notification = Notification.builder()
                .message(message)
                .senderUserId(senderId)
                .receiverUserId(receiverId)
                .build();
        log.info("sending notification to "+receiverId+" "+message);
        notificationRepository.save(notification);
    }
}
