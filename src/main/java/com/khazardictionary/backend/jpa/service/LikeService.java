package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.Like;
import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.repository.LikeRepository;
import com.khazardictionary.backend.jpa.repository.PostRepository;
import com.khazardictionary.backend.jpa.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author davut
 */
@Service
public class LikeService {

    LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
    }

    public void addLike(User user, Post post) {
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);
    }

    public Like getLike(Post post, User user) {
        return likeRepository.findLikeByPostAndUser(post, user);
    }

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public void deleteById(long id) {
        likeRepository.deleteById(id);
    }
}
