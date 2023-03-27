package com.khazardictionary.backend.jpa.controller;

import com.khazardictionary.backend.jpa.model.FileAttachment;
import com.khazardictionary.backend.jpa.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author davut
 */
@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/post-attachments")
    public FileAttachment savePostAttachment(MultipartFile file) {
        return fileService.savePostAttachment(file);
    }
}
