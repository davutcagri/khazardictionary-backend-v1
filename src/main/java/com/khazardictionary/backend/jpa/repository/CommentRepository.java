package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.Comment;
import com.khazardictionary.backend.jpa.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public Page<Comment> findByPost(Post post, Pageable page);

}
