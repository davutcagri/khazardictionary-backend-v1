package com.khazardictionary.backend.jpa.vm;

import com.khazardictionary.backend.shared.FileType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author davut
 */
@Data
public class PostSumbitVM {

    @Size(min = 1, max = 100, message = "{khazardictionary.post.sumbit.title.size.message}")
    @NotNull(message = "{khazardictionary.post.sumbit.title.notnull.message}")
    private String title;

    @Size(min = 1, max = 1000, message = "{khazardictionary.post.sumbit.content.size.message}")
    @NotNull(message = "{khazardictionary.post.sumbit.content.notnull.message}")
    private String content;

    @NotNull(message = "{khazardictionary.post.sumbit.category.notnull.message}")
    private String category;

    private long attachmentId;
}
