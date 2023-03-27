package com.khazardictionary.backend.jpa.vm;

import com.khazardictionary.backend.jpa.model.Verification;
import lombok.Data;

/**
 *
 * @author davut
 */
@Data
public class VerificationVM {
    
    private String verificationCode;
    
    public VerificationVM(Verification verification) {
        this.setVerificationCode(verification.getVerificationCode());
    }
}
