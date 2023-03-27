package com.khazardictionary.backend.jpa.controller;

import com.khazardictionary.backend.jpa.model.Like;
import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.service.LikeService;
import com.khazardictionary.backend.jpa.service.PostService;
import com.khazardictionary.backend.shared.CurrentUser;
import com.khazardictionary.backend.network.response.GenericResponse;
import com.khazardictionary.backend.jpa.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author davut
 */
@RestController
@RequestMapping("/api")
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    PostService postService;

    @PostMapping("/posts/{id:[0-9]+}/likes")
    public GenericResponse addLike(@CurrentUser User user, @PathVariable long id) {
        Post post = postService.getPost(id);
        List<Like> allLikes = likeService.getAllLikes();
        Like like = likeService.getLike(post, user);
        if(allLikes.contains(like)){
            likeService.deleteById(like.getId());
            return new GenericResponse("Unliked");
        }
        likeService.addLike(user, post);
        return new GenericResponse("Post liked");
    }   
}
