package com.smnk107.linkedIn.posts_service.controllers;

import com.smnk107.linkedIn.posts_service.auth.UserContextHolder;
import com.smnk107.linkedIn.posts_service.services.PostLikeService;
import com.smnk107.linkedIn.posts_service.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final PostLikeService postLikeService;
    @PostMapping("/{postId}")
    void likePost(@PathVariable Long postId)
    {

        long userId = UserContextHolder.getCurrentUserid();
        postLikeService.likePost(postId,userId);
    }

    @DeleteMapping("/{postId}")
    void unLikePost(@PathVariable Long postId)
    {
        long userId = UserContextHolder.getCurrentUserid();
        postLikeService.unlikeLikePost(postId,userId);
    }


}
