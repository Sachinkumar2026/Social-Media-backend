package com.sachin.social_backend.repository;

import com.sachin.social_backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUsername(String username);
    @Query("""
    SELECT p FROM Post p
    WHERE p.username IN :usernames
    ORDER BY p.createdAt DESC
""")
    Page<Post> findFeedPosts(
            @Param("usernames") List<String> usernames,
            Pageable pageable
    );
}
