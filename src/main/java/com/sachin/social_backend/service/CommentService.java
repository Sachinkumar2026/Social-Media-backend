package com.sachin.social_backend.service;


import com.sachin.social_backend.entity.Comment;
import com.sachin.social_backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public Comment addComment(Long postId,String username,String content){

        Comment comment = Comment.builder()
                .postId(postId)
                .username(username)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long postId){
        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }

    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
