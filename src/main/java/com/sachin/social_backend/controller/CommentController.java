package com.sachin.social_backend.controller;

import com.sachin.social_backend.entity.Comment;
import com.sachin.social_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public Comment addComment(@PathVariable Long postId, @RequestBody String content) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return commentService.addComment(postId,username,content);
    }

    @GetMapping("/{postId}")
    public List<Comment> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
