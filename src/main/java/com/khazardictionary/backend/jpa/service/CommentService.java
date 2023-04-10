package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.Comment;
import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.model.User;
import com.khazardictionary.backend.jpa.repository.CommentRepository;
import com.khazardictionary.backend.jpa.vm.CommentSendVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void sendComment(CommentSendVM commentSendVM, User user, Post post) {
        Comment comment = new Comment();
        comment.setTimestamp(new Date());
        comment.setContent(commentSendVM.getContent());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    public Page<Comment> getCommentsByPost(Post post, Pageable page) {
        return commentRepository.findByPost(post, page);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.getOne(id);
        commentRepository.delete(comment);
    }

}
