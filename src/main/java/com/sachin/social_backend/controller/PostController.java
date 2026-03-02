package com.sachin.social_backend.controller;


import com.sachin.social_backend.dto.CreatePostRequest;
import com.sachin.social_backend.entity.Post;
import com.sachin.social_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Post createPost(@RequestBody CreatePostRequest request){

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return postService.createPost(request.getContent(),username);
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/user/{username}")
    public List<Post> getPostByUser(@PathVariable String username){
        return postService.getPostByUser(username);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}
