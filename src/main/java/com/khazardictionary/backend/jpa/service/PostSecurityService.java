package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.model.User;
import java.util.Optional;

import com.khazardictionary.backend.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author davut
 */
@Service
public class PostSecurityService {

    @Autowired
    PostRepository postRepository;

    public boolean isAllowedToDelete(long id, User loggedInUser) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) {
            return false;
        }

        Post post = optionalPost.get();
        if (post.getUser().getId() != loggedInUser.getId()) {
            return false;
        }
        return true;
    }
}
