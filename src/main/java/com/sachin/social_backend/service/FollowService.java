package com.sachin.social_backend.service;

import com.sachin.social_backend.entity.Follow;
import com.sachin.social_backend.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public String followUser(String follower, String following) {

        if (follower.equals(following)) {
            return "You cannot follow yourself";
        }

        if (followRepository.existsByFollowerUsernameAndFollowingUsername(follower, following)) {
            return "Already following";
        }

        Follow follow = Follow.builder()
                .followerUsername(follower)
                .followingUsername(following)
                .createdAt(LocalDateTime.now())
                .build();

        followRepository.save(follow);

        return "Followed successfully";
    }

    public String unfollowUser(String follower,String following){
        followRepository.deleteByFollowerUsernameAndFollowingUsername(follower,following);
        return "Unfollowed successfully";
    }
    public List<Follow> getFollowers(String username){
        return followRepository.findByFollowingUsername(username);
    }
    public List<Follow> getFollowing(String username){
        return followRepository.findByFollowerUsername(username);
    }
}
