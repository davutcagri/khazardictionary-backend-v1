package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.Credentials;
import com.khazardictionary.backend.jpa.model.Token;
import com.khazardictionary.backend.jpa.model.User;
import com.khazardictionary.backend.jpa.repository.TokenRepository;
import com.khazardictionary.backend.jpa.repository.UserRepository;
import com.khazardictionary.backend.jpa.vm.UserVM;
import com.khazardictionary.backend.network.exception.AuthException;
import com.khazardictionary.backend.network.response.AuthResponse;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author davut
 */
@Service
public class AuthService {
    
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    TokenRepository tokenRepository;
    
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }
    
    public AuthResponse authenticate(Credentials credentials) {
        User inDB = userRepository.findByUsername(credentials.getUsername());
        
        if (inDB == null) {
            throw new AuthException();
        }
        
        boolean matches = passwordEncoder.matches(credentials.getPassword(), inDB.getPassword());
        
        if (!matches) {
            throw new AuthException();
        }
        
        UserVM userVM = new UserVM(inDB);
        String token = generateRandomToken();
        
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUser(inDB);
        tokenRepository.save(tokenEntity);
        
        AuthResponse response = new AuthResponse();
        response.setUser(userVM);
        response.setToken(token);
        return response;
    }
    
    @Transactional
    public UserDetails getUserDetails(String token) {
        Optional<Token> optionalToken = tokenRepository.findById(token);
        if (!optionalToken.isPresent()) {
            return null;
        }
        return optionalToken.get().getUser();
    }
    
    public String generateRandomToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public void clearToken(String token) {
        tokenRepository.deleteById(token);
    }
}
