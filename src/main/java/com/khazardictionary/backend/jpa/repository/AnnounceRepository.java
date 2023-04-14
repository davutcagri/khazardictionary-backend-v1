package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.Announce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {

    Announce findByDate(Date date);

}
