package com.khazardictionary.backend.jpa.vm;

import com.khazardictionary.backend.jpa.model.User;
import lombok.Data;

/**
 *
 * @author davut
 */
@Data
public class UserVM {

    private String username;

    private String displayName;

    private String image;

    public UserVM(User user) {
        this.setUsername(user.getUsername());
        this.setDisplayName(user.getDisplayName());
        this.setImage(user.getImage());
    }
}
