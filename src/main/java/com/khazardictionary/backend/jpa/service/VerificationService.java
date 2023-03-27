package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.Verification;
import com.khazardictionary.backend.jpa.repository.VerificationRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author davut
 */
@Service
public class VerificationService {
    
    VerificationRepository verificationRepository;
    
    public VerificationService(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }
    
    public void saveVerification(String email, String verificationCode) {
        Verification verification = new Verification();
        verification.setEmail(email);
        verification.setVerificationCode(verificationCode);
        verificationRepository.save(verification);
    }
    
    public Verification getVerificationByEmail(String email) {
        return verificationRepository.findCodeByEmail(email);
    }
    
    public void deleteVerification(String email) {
        Verification verification = verificationRepository.findCodeByEmail(email);
        verificationRepository.delete(verification);
    }
}
