package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author davut
 */
public interface TokenRepository extends JpaRepository<Token, String>{
    
}
