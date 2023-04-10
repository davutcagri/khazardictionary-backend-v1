package com.khazardictionary.backend.jpa.controller;

import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.model.User;
import com.khazardictionary.backend.jpa.service.CommentService;
import com.khazardictionary.backend.jpa.service.PostService;
import com.khazardictionary.backend.jpa.vm.CommentSendVM;
import com.khazardictionary.backend.jpa.vm.CommentVM;
import com.khazardictionary.backend.shared.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @PostMapping("/posts/{id:[0-9]+}/comments")
    public void sendComment(@Valid @RequestBody CommentSendVM commentSendVM, @CurrentUser User user, @PathVariable long id) {
        Post post = postService.getPost(id);
        commentService.sendComment(commentSendVM, user, post);
    }

    @GetMapping("/posts/{id:[0-9]+}/comments")
    public Page<CommentVM> getCommentsByPost(
            @PathVariable Long id, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        Post post = postService.getPost(id);
        return commentService.getCommentsByPost(post, page).map(CommentVM::new);
    }

    @DeleteMapping("/comments/{id:[0-9]+}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
