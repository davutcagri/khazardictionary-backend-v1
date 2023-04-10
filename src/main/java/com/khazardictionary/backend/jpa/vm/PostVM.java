package com.khazardictionary.backend.jpa.vm;

import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.model.User;
import lombok.Data;

/**
 *
 * @author davut
 */
@Data
public class PostVM {

    private long id;

    private String title;

    private String content;

    private long timestamp;
    
    private String category;

    private UserVM user;

    private FileAttachmentVM fileAttachment;

    private long likeCount = 0;

    private boolean iLiked = false;

    private long commentCount = 0;

    public PostVM(Post post, User user) {
        this.setId(post.getId());
        this.setTitle(post.getTitle());
        this.setContent(post.getContent());
        this.setTimestamp(post.getTimestamp().getTime());
        this.setCategory(post.getCategory());
        this.setUser(new UserVM(post.getUser()));
        this.setLikeCount(post.getLikes().size());
        this.setCommentCount(post.getComments().size());
        if (post.getFileAttachment() != null) {
            this.fileAttachment = new FileAttachmentVM(post.getFileAttachment());
        }

        boolean loggedInUserLikedThisHoax = post.getLikes()
                .stream().filter(currentPost -> currentPost.getUser().getUsername().equals(user.getUsername())).findAny().isPresent();
        this.setILiked(loggedInUserLikedThisHoax);
    }
}
