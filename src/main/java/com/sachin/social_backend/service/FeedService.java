package com.sachin.social_backend.service;


import com.sachin.social_backend.entity.Follow;
import com.sachin.social_backend.entity.Post;
import com.sachin.social_backend.repository.FollowRepository;
import com.sachin.social_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FollowRepository followRepository;
    private final PostRepository postRepository;
    private final RedisTemplate<String,Object> redisTemplate;

    public List<Post> getFeed(String username, Pageable pageable){

        String cacheKey = "feed:" + username;

        Object cachedFeed = redisTemplate.opsForValue().get(cacheKey);

        if(cachedFeed != null){
            return (List<Post>) cachedFeed;
        }

        List<String> following = followRepository
                .findByFollowerUsername(username)
                .stream()
                .map(Follow::getFollowingUsername)
                .toList();

        Page<Post> page = postRepository.findFeedPosts(following,pageable);

        List<Post> posts = page.getContent();

        redisTemplate.opsForValue().set(cacheKey, posts);

        return posts;
    }
}
