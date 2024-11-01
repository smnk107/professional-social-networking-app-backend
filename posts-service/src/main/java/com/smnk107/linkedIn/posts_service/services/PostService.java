package com.smnk107.linkedIn.posts_service.services;

import com.smnk107.linkedIn.posts_service.dto.PostCreateRequestDto;
import com.smnk107.linkedIn.posts_service.dto.PostDto;
import com.smnk107.linkedIn.posts_service.entity.Post;
import com.smnk107.linkedIn.posts_service.exceptions.ResourceNotFoundException;
import com.smnk107.linkedIn.posts_service.repository.PostRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {

        Post post = modelMapper.map(postCreateRequestDto,Post.class);
        post.setUserId(userId);

        Post savedPost = postRepository.save(post);

        return modelMapper.map(savedPost,PostDto.class);
    }

    public PostDto getPostById(Long postId) {

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
