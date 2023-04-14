package com.khazardictionary.backend.jpa.vm;

import com.khazardictionary.backend.jpa.model.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author davut
 */
@Data
public class UserVM {

    private String username;

    private String displayName;

    private String image;

    private Stream<String> roleName;

    public UserVM(User user) {
        this.setUsername(user.getUsername());
        this.setDisplayName(user.getDisplayName());
        this.setImage(user.getImage());
        this.setRoleName(user.getRoles().stream().map(role -> role.getRoleName()));
    }
}
