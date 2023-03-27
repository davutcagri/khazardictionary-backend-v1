package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author davut
 */
public interface VerificationRepository extends JpaRepository<Verification, Long>{
    
    Verification findCodeByEmail(String email);
    
    
}
