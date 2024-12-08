package com.smnk107.notification_service.consumer;

import com.smnk107.notification_service.clientEntities.Person;
import com.smnk107.notification_service.clients.ConnectionClient;
import com.smnk107.notification_service.entity.Notification;
//import com.smnk107.notification_service.post_service.event.PostCreatedEvent;
//import com.smnk107.notification_service.post_service.event.PostLikedEvent;
import com.smnk107.linkedIn.posts_service.event.PostCreatedEvent;
import com.smnk107.linkedIn.posts_service.event.PostLikedEvent;
import com.smnk107.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceConsumer {

    private final ConnectionClient connectionClient;
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "post-created-topic")
    void handlePostCreatedTopic(PostCreatedEvent postCreatedEvent)
    {
        List<Person> list = connectionClient.getFirstDegreeConnections(postCreatedEvent.getCreatorId()).getBody();
        for(Person person: list)
        {
            log.info("inside loop to send notication :"+person.getId());
            String message = String.format("User with id: %d has posted !",postCreatedEvent.getCreatorId());
            sendNotification(person.getUserId(), postCreatedEvent.getCreatorId(),message);
        }

    }


    @KafkaListener(topics = "post-liked-topic")
    void handlePostLikedTopic(PostLikedEvent postLikedEvent)
    {
        log.info("sending like notification");
        String message = String.format("your post with id %d hasbeen liked by %d",postLikedEvent.getPostId(),postLikedEvent.getLikedByUserid());

        sendNotification(postLikedEvent.getCreatorId(), postLikedEvent.getLikedByUserid(), message);
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
