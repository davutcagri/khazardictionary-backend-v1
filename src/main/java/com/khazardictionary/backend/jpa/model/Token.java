package com.khazardictionary.backend.jpa.model;

import com.khazardictionary.backend.jpa.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author davut
 */
@Entity
@Data
public class Token {

    @Id
    private String token;

    @ManyToOne
    private User user;
}
