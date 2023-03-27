package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.Like;
import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author davut
 */
public interface LikeRepository extends JpaRepository<Like, Long>{
    
    public Like findLikesByPost(Post post);
    
    public Like findLikeByPostAndUser(Post post, User user);
}
