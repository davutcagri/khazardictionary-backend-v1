package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author davut
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    
    public User findByEmail(String email);

    public Page<User> findByUsernameNot(String username, Pageable page);
}
