package com.khazardictionary.backend.jpa.model;

import com.khazardictionary.backend.unique.UniqueEmail;
import com.khazardictionary.backend.unique.UniqueUsername;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @Email(message = "{khazardictionary.validation.constraints.emailType.message}")
    @NotNull(message = "{khazardictionary.validation.constraints.NotNull.message.email}")
    @Pattern(regexp = "^([_A-Za-z0-9-+]+\\.?[_A-Za-z0-9-+]+@(gmail.com))$", message = "{khazardictionary.validation.constraints.Pattern.message.email}")
    @UniqueEmail
    private String email;
    
    @NotNull(message = "{khazardictionary.validation.constraints.NotNull.message.username}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    private String username;

    @NotNull(message = "{khazardictionary.validation.constraints.NotNull.message.displayName}")
    @Size(min = 4, max = 255)
    private String displayName;

    @NotNull(message = "{khazardictionary.validation.constraints.NotNull.message.password}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{khazardictionary.validation.constraints.Pattern.message.password}")
    @Size(min = 8, max = 255)
    private String password;
    
    private String image;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Token> tokens;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Like> likes;

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
