package com.sachin.social_backend.service;


import com.sachin.social_backend.entity.Follow;
import com.sachin.social_backend.entity.Post;
import com.sachin.social_backend.repository.FollowRepository;
import com.sachin.social_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FollowRepository followRepository;
    private final PostRepository postRepository;

    public Page<Post> getFeed(String username, Pageable pageable){

        List<String> following = followRepository
                .findByFollowerUsername(username)
                .stream()
                .map(Follow::getFollowingUsername)
                .collect(Collectors.toList());

        return postRepository.findFeedPosts(following, pageable);
    }
}
