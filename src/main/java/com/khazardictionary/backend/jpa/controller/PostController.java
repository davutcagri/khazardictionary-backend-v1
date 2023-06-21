package com.khazardictionary.backend.jpa.controller;

import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.service.PostService;
import com.khazardictionary.backend.jpa.service.UserService;
import com.khazardictionary.backend.jpa.vm.PostSumbitVM;
import com.khazardictionary.backend.jpa.vm.PostVM;
import com.khazardictionary.backend.shared.CurrentUser;
import com.khazardictionary.backend.network.response.GenericResponse;
import com.khazardictionary.backend.jpa.model.User;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author davut
 */
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/posts")
    public GenericResponse createPost(@Valid @RequestBody PostSumbitVM postSumbitVM, @CurrentUser User user) {
        postService.save(postSumbitVM, user);
        return new GenericResponse("Post created");
    }

    @GetMapping("/posts")
    public Page<PostVM> getPosts(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page, @CurrentUser User user) {
        return postService.getPosts(page).map(post -> {
            return new PostVM(post, user);
        });
    }

    @GetMapping("/posts/view/{id:[0-9]+}")
    public PostVM getPostById (@PathVariable Long id, @CurrentUser User user) {
        Post post = postService.getPost(id);
        return new PostVM(post, user);
    }

    @GetMapping("/users/{username}/posts")
    public Page<PostVM> getPostsByUsername(@PathVariable String username,
                                           @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page, @CurrentUser User user) {
        return postService.getPostsByUsername(username, page).map(post -> {
            return new PostVM(post, user);
        });
    }
    
    @GetMapping("/{category}/posts")
    public Page<PostVM> getPostsByCategories(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page,
            @PathVariable String category,
            @CurrentUser User user) {
        return postService.getPostsByCategories(category, page).map(post -> {
           return new PostVM(post, user);
        });
    }

    @GetMapping({"/posts/{id:[0-9]+}", "/users/{username}/posts/{id:[0-9]+}"})
    public ResponseEntity<?> getPostsRelative(
            @PathVariable Long id,
            @PathVariable(required = false) String username,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page,
            @RequestParam(name = "count", required = false, defaultValue = "false") boolean count,
            @RequestParam(name = "direction", defaultValue = "before") String direction,
            @CurrentUser User user) {

        if (count) {
            long newPostsCount = postService.getNewPostsCount(id, username);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newPostsCount);
            return ResponseEntity.ok(response);
        }

        if (direction.equals("after")) {
            List<PostVM> newPostsVM = postService.getNewPosts(id, username, page.getSort())
                    .stream().map(post -> {
                        return new PostVM(post, user);
                    }).collect(Collectors.toList());
            return ResponseEntity.ok(newPostsVM);
        }

        return ResponseEntity.ok(postService.getOldPosts(id, username, page).map(post -> {
            return new PostVM(post, user);
        }));
    }

    @DeleteMapping("/posts/{id:[0-9]+}")
    @PreAuthorize("@postSecurityService.isAllowedToDelete(#id, principal)")
    public GenericResponse deletePost(@PathVariable long id, @CurrentUser User loggedInUser) {
        postService.delete(id);
        return new GenericResponse("Post removed");
    }

    @PutMapping("/posts/{id:[0-9]+}/commentsLock")
    public PostVM updatePostCommentLock(@PathVariable Long id, @CurrentUser User user) {
        Post post = postService.updatePostCommentLock(id);
        return new PostVM(post, user);
    }

}
