package com.khazardictionary.backend.jpa.controller;

import com.khazardictionary.backend.network.response.AuthResponse;
import com.khazardictionary.backend.jpa.service.AuthService;
import com.khazardictionary.backend.jpa.model.Credentials;
import com.khazardictionary.backend.network.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author davut
 */
@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    AuthService authService;
    
    @PostMapping("/auth/token")
    public AuthResponse handleAuthentication(@RequestBody Credentials credentials) {
        return authService.authenticate(credentials);
    }
    
    @PostMapping("/logout")
    public GenericResponse handleLogout(@RequestHeader(name = "Authorization") String authorization) {
        String token = authorization.substring(7);
        authService.clearToken(token);
        return new GenericResponse("Logout Success");
    }
}
