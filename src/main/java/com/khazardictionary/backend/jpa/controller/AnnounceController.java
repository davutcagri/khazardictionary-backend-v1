package com.khazardictionary.backend.jpa.controller;

import com.khazardictionary.backend.jpa.model.Announce;
import com.khazardictionary.backend.jpa.service.AnnounceService;
import com.khazardictionary.backend.jpa.vm.AnnounceSumbitVM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AnnounceController {

    @Autowired
    AnnounceService announceService;

    @PostMapping("/announce")
    public void sendAnnounce(@Valid @RequestBody AnnounceSumbitVM announceSumbitVM) {
        announceService.sendAnnounce(announceSumbitVM);
    }

    @GetMapping("/announce")
    public Page<Announce> getAnnounces(Pageable pageable) {
        return announceService.getAnnounces(pageable);
    }

    @DeleteMapping("/announce/{id:[0-9]+}")
    public void deleteAnnounce(@PathVariable Long id) {
        announceService.deleteAnnounce(id);
    }

}
