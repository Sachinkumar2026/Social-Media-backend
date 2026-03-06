package com.sachin.social_backend.service;

import com.sachin.social_backend.entity.Post;
import com.sachin.social_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final RedisTemplate<String,Object> redisTemplate;

    public Post createPost(String content,String username){
        String key = "post_rate_limit:" + username;
        Long count = redisTemplate.opsForValue().increment(key);

        if(count == 1){
            redisTemplate.expire(key,60, TimeUnit.SECONDS);
        }
        if(count > 5){
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,
                    "Post limit exceeded. try again later.");
        }
        Post post = Post.builder()
                .content(content)
                .username(username)
                .createdAt(LocalDateTime.now())
                .build();

        redisTemplate.delete("feed:" + username);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    public List<Post> getPostsByUser(String username) {
        return postRepository.findByUsername(username);
    }

    public void deletePost(Long id){
        postRepository.deleteById(id);
    }
    public Page<Post> getPosts(Pageable pageable){
        return  postRepository.findAll(pageable);
    }
}
