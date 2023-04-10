package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author davut
 */
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    public Page<Post> findByUserAndId(User user, Long id, Pageable page);

    public Page<Post> findByUser(User user, Pageable page);
    
    public Page<Post> findByCategory(String category, Pageable page);
}
