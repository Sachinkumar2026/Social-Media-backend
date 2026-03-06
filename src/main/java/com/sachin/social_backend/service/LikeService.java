package com.sachin.social_backend.service;

import com.sachin.social_backend.entity.Like;
import com.sachin.social_backend.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public String likePost(Long postId,String username){
        if(likeRepository.existsByPostIdAndUsername(postId,username)){
            return "Already Liked";
        }

        Like like = Like.builder()
                .postId(postId)
                .username(username)
                .createdAt(LocalDateTime.now())
                .build();

        likeRepository.save(like);
        return "Post liked";
    }

    @Transactional
    public String unlikePost(Long postId, String username){
        likeRepository.deleteByPostIdAndUsername(postId,username);
        return "Post unliked";
    }

    public long getLikes(Long postId){
        return likeRepository.countByPostId(postId);
    }

    public List<Like> getPostLikes(Long postId){
        return likeRepository.findByPostId(postId);
    }
}
