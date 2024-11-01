package com.smnk107.linkedIn.posts_service.repository;

import com.smnk107.linkedIn.posts_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long> {

    boolean existsByPostIdAndUserId(Long postId, Long userId);
    @Transactional
    void deleteByPostIdAndUserId(Long postId, Long userId);

}
