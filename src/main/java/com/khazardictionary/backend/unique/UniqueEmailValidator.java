package com.khazardictionary.backend.unique;

import com.khazardictionary.backend.jpa.model.User;
import com.khazardictionary.backend.jpa.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author davut
 */
class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cvc) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return false;
        }
        return true;
    }
    
}
