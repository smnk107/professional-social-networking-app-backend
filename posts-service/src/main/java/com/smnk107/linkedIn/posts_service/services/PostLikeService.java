package com.smnk107.linkedIn.posts_service.services;

import com.smnk107.linkedIn.posts_service.entity.Post;
import com.smnk107.linkedIn.posts_service.entity.PostLike;
import com.smnk107.linkedIn.posts_service.exceptions.BadRequestException;
import com.smnk107.linkedIn.posts_service.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    final private PostService postService;
    final private PostLikeRepository postLikeRepository;
    public void likePost(Long postId, Long userId)
    {
        boolean exists = postService.doesExist(postId);

        if(!exists)
        {
            throw new RuntimeException("post not found to like");
        }

        boolean alreadyLiked = postLikeRepository.existsByPostIdAndUserId(postId,userId);

        if(alreadyLiked)
            throw new BadRequestException("post already liked by user");

        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();

        postLikeRepository.save(postLike);

    }

    public void unlikeLikePost(Long postId, Long userId) {

        boolean exists = postService.doesExist(postId);

        if(!exists)
        {
            throw new RuntimeException("post not found to unlike");
        }

        boolean alreadyLiked = postLikeRepository.existsByPostIdAndUserId(postId,userId);

        if(!alreadyLiked)
            throw new BadRequestException("post not liked previously");

        postLikeRepository.deleteByPostIdAndUserId(postId,userId);
    }
}
