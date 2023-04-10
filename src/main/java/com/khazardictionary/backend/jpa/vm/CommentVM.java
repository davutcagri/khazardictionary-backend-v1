package com.khazardictionary.backend.jpa.vm;

import com.khazardictionary.backend.jpa.model.Comment;
import lombok.Data;

@Data
public class CommentVM {

    private long id;

    private String content;

    private UserVM user;

    private PostVM post;

    public CommentVM(Comment comment) {
        this.setId(comment.getId());
        this.setContent(comment.getContent());
        this.setUser(new UserVM(comment.getUser()));
        this.setPost(new PostVM(comment.getPost(), comment.getUser()));
    }

}
