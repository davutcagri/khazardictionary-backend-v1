package com.khazardictionary.backend.jpa.vm;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author davut
 */
@Data
public class PostSumbitVM {

    @Size(min=1, max=100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;
    
    private String category;
    
    private long attachmentId;
    
    private long eventId;
}
