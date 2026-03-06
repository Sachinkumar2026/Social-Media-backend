package com.sachin.social_backend.controller;


import com.sachin.social_backend.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public String likePost(@PathVariable Long postId){

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return likeService.likePost(postId,username);
    }

    @DeleteMapping("/{postId}")
    public String unlikePost(@PathVariable Long postId){

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        System.out.println("User deleting like: " + username);

        return likeService.unlikePost(postId, username);
    }

    @GetMapping("/{postId}")
    public long getLikes(@PathVariable Long postId){
        return likeService.getLikes(postId);
    }
}
