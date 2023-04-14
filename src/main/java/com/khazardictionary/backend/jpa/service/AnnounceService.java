package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.Announce;
import com.khazardictionary.backend.jpa.model.FileAttachment;
import com.khazardictionary.backend.jpa.repository.AnnounceRepository;
import com.khazardictionary.backend.jpa.vm.AnnounceSumbitVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnnounceService {

    AnnounceRepository announceRepository;

    public AnnounceService(AnnounceRepository announceRepository) {
        this.announceRepository = announceRepository;
    }

    public void sendAnnounce(AnnounceSumbitVM announceSumbitVM) {
        announceRepository.deleteAll();
        Announce announce = new Announce();
        announce.setType(announceSumbitVM.getType());
        announce.setContent(announceSumbitVM.getContent());
        announce.setDate(new Date());
        announceRepository.save(announce);
    }

    public Page<Announce> getAnnounces(Pageable pageable) {
        return announceRepository.findAll(pageable);
    }

    public void deleteAnnounce(Long id) {
        announceRepository.deleteById(id);
    }

}
