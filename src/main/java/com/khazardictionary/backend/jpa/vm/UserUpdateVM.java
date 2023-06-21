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
    
    @NotNull(message = "{khazardictionary.user.displayname.notnull.message}")
    @Size(min = 4, max = 30)
    private String displayName;

    private String image;
}
