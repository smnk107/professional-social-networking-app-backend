package com.smnk107.linkedIn.posts_service.event;

import lombok.*;

@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class PostLikedEvent {
    Long postId;
    Long creatorId;
    Long likedByUserid;
}
