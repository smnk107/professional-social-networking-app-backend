package com.smnk107.linkedIn.posts_service.services;

import com.smnk107.linkedIn.posts_service.auth.UserContextHolder;
import com.smnk107.linkedIn.posts_service.dto.PostCreateRequestDto;
import com.smnk107.linkedIn.posts_service.dto.PostDto;
import com.smnk107.linkedIn.posts_service.entity.Post;
import com.smnk107.linkedIn.posts_service.event.PostCreatedEvent;
import com.smnk107.linkedIn.posts_service.exceptions.ResourceNotFoundException;
import com.smnk107.linkedIn.posts_service.repository.PostRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long,PostCreatedEvent> postCreatedKafkaTemplate;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto) {

        Long userId = UserContextHolder.getCurrentUserid();

        Post post = modelMapper.map(postCreateRequestDto,Post.class);
        post.setUserId(userId);

        Post savedPost = postRepository.save(post);

        PostCreatedEvent postCreatedEvent = PostCreatedEvent
                                            .builder()
                                            .creatorId(userId)
                                            .content(savedPost.getContent())
                                            .postId(savedPost.getId())
                                            .build();

        postCreatedKafkaTemplate.send("post-created-topic",savedPost.getId(),postCreatedEvent);

        return modelMapper.map(savedPost,PostDto.class);
    }

    public PostDto getPostById(Long postId) {

        log.info("user id is"+ UserContextHolder.getCurrentUserid());
        Post post =postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with id :"+postId));
        return  modelMapper.map(post,PostDto.class);
    }

    public boolean doesExist(Long postId)
    {
        return postRepository.existsById(postId);
    }

    public List<PostDto> getAllPostForUser(Long userId) {
        List<Post> list = postRepository.findByUserId(userId);

        return list.stream()
                .map((elem)->modelMapper.map(elem,PostDto.class))
                .collect(Collectors.toList());

    }
}
