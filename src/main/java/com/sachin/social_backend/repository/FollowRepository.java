package com.sachin.social_backend.repository;

import com.sachin.social_backend.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    List<Follow> findByFollowerUsername(String followerUsername);
    List<Follow> findByFollowingUsername(String followingUsername);

    boolean existsByFollowerUsernameAndFollowingUsername(
            String followerUsername,
            String followingUsername
    );
    void deleteByFollowerUsernameAndFollowingUsername(
            String followerUsername,
            String followingUsername
    );
}
