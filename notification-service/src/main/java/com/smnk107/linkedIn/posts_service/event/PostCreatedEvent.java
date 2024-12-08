package com.smnk107.linkedIn.posts_service.event;

import lombok.*;

@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class PostCreatedEvent {

    Long creatorId;
    String content;
    Long postId;
}
