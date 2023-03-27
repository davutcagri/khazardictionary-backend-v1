package com.khazardictionary.backend.network.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author davut
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorizationException extends RuntimeException{
    
}
