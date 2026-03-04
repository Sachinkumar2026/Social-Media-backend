package com.sachin.social_backend.service;

import com.sachin.social_backend.entity.Post;
import com.sachin.social_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(String content,String username){
        Post post = Post.builder()
                .content(content)
                .username(username)
                .createdAt(LocalDateTime.now())
                .build();
        return postRepository.save(post);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public List<Post> getPostByUser(String username){
        return postRepository.findByUsername(username);
    }

    public void deletePost(Long id){
        postRepository.deleteById(id);
    }
    public Page<Post> getPosts(Pageable pageable){
        return  postRepository.findAll(pageable);
    }
}
