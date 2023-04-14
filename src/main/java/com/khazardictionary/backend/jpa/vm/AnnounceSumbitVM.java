package com.khazardictionary.backend.jpa.vm;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class AnnounceSumbitVM {
    private String type;

    @Size(min = 1, max = 1000)
    private String content;

}
