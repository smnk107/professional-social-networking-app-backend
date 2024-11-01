package com.smnk107.linkedIn.posts_service.controllers;

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
      postLikeService.likePost(postId,1L);
    }

    @DeleteMapping("/{postId}")
    void unLikePost(@PathVariable Long postId)
    {
        postLikeService.unlikeLikePost(postId,1L);
    }


}
