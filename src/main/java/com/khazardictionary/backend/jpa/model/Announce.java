package com.khazardictionary.backend.jpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "announces")
public class Announce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @Size(min = 1, max = 1000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
