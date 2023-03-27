package com.khazardictionary.backend.network.response;

import com.khazardictionary.backend.jpa.vm.UserVM;
import lombok.Data;

/**
 *
 * @author davut
 */
@Data
public class AuthResponse {
    
    private String token;
    
    private UserVM user;
}
