package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.FileAttachment;
import com.khazardictionary.backend.jpa.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author davut
 */
public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {
    
    List<FileAttachment> findByDateBeforeAndPostIsNull(Date date);

    List<FileAttachment> findByPostUser(User user);
}
