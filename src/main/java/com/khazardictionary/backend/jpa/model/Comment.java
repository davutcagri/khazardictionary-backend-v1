package com.khazardictionary.backend.jpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Size(min = 1, max = 1000)
    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

}
