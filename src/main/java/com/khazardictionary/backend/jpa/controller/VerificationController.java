package com.khazardictionary.backend.jpa.controller;

import com.khazardictionary.backend.jpa.model.Verification;
import com.khazardictionary.backend.jpa.service.EmailSenderService;
import com.khazardictionary.backend.network.response.GenericResponse;
import com.khazardictionary.backend.jpa.service.VerificationService;
import com.khazardictionary.backend.jpa.vm.VerificationVM;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author davut
 */
@RestController
@RequestMapping("/api")
public class VerificationController {

    @Autowired
    VerificationService verificationService;
    
    @Autowired
    EmailSenderService emailSenderService;

    @PostMapping("/verification/{email}")
    public GenericResponse saveVerification(@PathVariable String email) {
        Random random = new Random();
        int number = random.nextInt(999999);
        String verificationCode = String.format("%06d", number);
        verificationService.saveVerification(email, verificationCode);
        
        emailSenderService.sendEmail(email, "Verification Code", "Your verification code: " + verificationCode);
        
        return new GenericResponse("Email sent");
    }
    
    @GetMapping("/verification/{email}")
    public VerificationVM getVerificationByEmail(@PathVariable String email) {
        Verification verification = verificationService.getVerificationByEmail(email);
        return new VerificationVM(verification);
    }
    
    @DeleteMapping("/verification/{email}")
    public void deleteVerification(@PathVariable String email) {
        verificationService.deleteVerification(email);
    }
}
