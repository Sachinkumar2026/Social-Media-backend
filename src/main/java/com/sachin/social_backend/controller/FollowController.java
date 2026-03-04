package com.sachin.social_backend.controller;


import com.sachin.social_backend.entity.Follow;
import com.sachin.social_backend.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{username}")
    public String followUser(@PathVariable String username){

        String currentUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return followService.followUser(currentUser,username);
    }
    @DeleteMapping("/{username}")
    public String unfollowerUser(@PathVariable String username){

        String currentUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return followService.unfollowUser(currentUser,username);
    }

    @GetMapping("/followers/{username}")
    public List<Follow> getFollowers(@PathVariable String username){
        return followService.getFollowers(username);
    }

    @GetMapping("/following/{username}")
    public List<Follow> getFollowing(@PathVariable String username){
        return followService.getFollowing(username);
    }
}
