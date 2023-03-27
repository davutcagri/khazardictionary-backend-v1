package com.khazardictionary.backend.jpa.vm;

import com.khazardictionary.backend.jpa.model.FileAttachment;
import lombok.Data;

/**
 *
 * @author davut
 */
@Data
public class FileAttachmentVM {

    private String name;
    
    private String fileType;

    public FileAttachmentVM(FileAttachment fileAttachment) {
        this.setName(fileAttachment.getName());
        this.fileType = fileAttachment.getFileType();
    }
}
