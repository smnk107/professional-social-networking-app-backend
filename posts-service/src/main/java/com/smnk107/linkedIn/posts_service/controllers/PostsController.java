package com.smnk107.linkedIn.posts_service.controllers;

import com.smnk107.linkedIn.posts_service.auth.UserContextHolder;
import com.smnk107.linkedIn.posts_service.auth.UserInterceptor;
import com.smnk107.linkedIn.posts_service.clientEntities.Person;
import com.smnk107.linkedIn.posts_service.clients.ConnectionClient;
import com.smnk107.linkedIn.posts_service.dto.PostCreateRequestDto;
import com.smnk107.linkedIn.posts_service.dto.PostDto;
import com.smnk107.linkedIn.posts_service.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostService postService;
    private final ConnectionClient connectionClient;

    @PostMapping
    ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto)
    {
        PostDto createdPost = postService.createPost(postCreateRequestDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    ResponseEntity<PostDto> getPost(@PathVariable Long postId)
    {
            log.info("The user id through request header "+UserContextHolder.getCurrentUserid());
            PostDto postDto = postService.getPostById(postId);
            return ResponseEntity.ok(postDto);
    }

    @GetMapping("users/{userId}/allPosts")
    ResponseEntity<List<PostDto>> getAllPostOfUser(@PathVariable Long userId)
    {
        List<PostDto> list = postService.getAllPostForUser(userId);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/firstDegreeConnections")
    public ResponseEntity<List<Person>> getFirstDegreeConnections()
    {
        Long userId = UserContextHolder.getCurrentUserid();
        return connectionClient.getFirstDegreeConnections(UserContextHolder.getCurrentUserid());
    }
}
