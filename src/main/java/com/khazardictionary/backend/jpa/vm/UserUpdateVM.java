package com.khazardictionary.backend.jpa.vm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.khazardictionary.backend.shared.FileType;

/**
 *
 * @author davut
 */
@Data
public class UserUpdateVM {
    
    @NotNull(message = "{khazardictionary.validation.constraints.NotNull.message.displayName}")
    @Size(min = 4, max = 255)
    private String displayName;

    @FileType(types = {"jpeg", "png"})
    private String image;
}
