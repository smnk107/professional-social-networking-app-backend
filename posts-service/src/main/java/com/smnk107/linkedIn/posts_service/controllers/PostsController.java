package com.smnk107.linkedIn.posts_service.controllers;

import com.smnk107.linkedIn.posts_service.dto.PostCreateRequestDto;
import com.smnk107.linkedIn.posts_service.dto.PostDto;
import com.smnk107.linkedIn.posts_service.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts/core")
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;
    @PostMapping
    ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto)
    {
        PostDto createdPost = postService.createPost(postCreateRequestDto,1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    ResponseEntity<PostDto> getPost(@PathVariable Long postId)
    {
            PostDto postDto = postService.getPostById(postId);
            return ResponseEntity.ok(postDto);
    }

    @GetMapping("users/{userId}/allPosts")
    ResponseEntity<List<PostDto>> getAllPostOfUser(@PathVariable Long userId)
    {
        List<PostDto> list = postService.getAllPostForUser(userId);
        return ResponseEntity.ok(list);
    }
}
