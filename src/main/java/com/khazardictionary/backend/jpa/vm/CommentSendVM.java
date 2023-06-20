package com.khazardictionary.backend.jpa.vm;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentSendVM {

    @Size(min = 1, max = 1000, message = "{khazardictionary.comment.content.size.message}")
    private String content;
}
