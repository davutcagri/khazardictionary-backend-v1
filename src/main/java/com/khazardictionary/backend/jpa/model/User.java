package com.khazardictionary.backend.jpa.model;

import com.khazardictionary.backend.unique.UniqueEmail;
import com.khazardictionary.backend.unique.UniqueUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author davut
 */
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email(message = "{khazardictionary.user.email.type.message}")
    @NotNull(message = "{khazardictionary.user.email.notnull.message}")
    @Pattern(regexp = "^([_A-Za-z0-9-+]+\\.?[_A-Za-z0-9-+]+@(khazar.org))$", message = "{khazardictionary.user.email.pattern.message}")
    @UniqueEmail
    private String email;
    
    @NotNull(message = "{khazardictionary.user.username.notnull.message}")
    @Size(min = 4, max = 255, message = "{khazardictionary.user.username.size.message}")
    @UniqueUsername
    private String username;

    @NotNull(message = "{khazardictionary.user.displayname.notnull.message}")
    @Size(min = 4, max = 255, message = "{khazardictionary.user.displayname.size.message}")
    private String displayName;

    @NotNull(message = "{khazardictionary.user.password.notnull.message}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{khazardictionary.user.password.pattern.message}")
    @Size(min = 8, max = 255, message = "{khazardictionary.user.password.size.message}")
    private String password;
    
    private String image;

    private boolean verifiedAccount = false;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Token> tokens;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Like> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
