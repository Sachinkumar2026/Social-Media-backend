package com.sachin.social_backend.repository;

import com.sachin.social_backend.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    boolean existsByPostIdAndUsername(Long postId,String username);
    void deleteByPostIdAndUsername(Long postId,String username);

    List<Like> findByPostId(Long postId);
    long countByPostId(Long postId);
}
