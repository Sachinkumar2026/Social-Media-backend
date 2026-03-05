package com.sachin.social_backend.controller;


import com.sachin.social_backend.entity.Post;
import com.sachin.social_backend.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;


    @GetMapping
    public List<Post> getFeed(Pageable pageable){

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return feedService.getFeed(username,pageable);
    }
}
