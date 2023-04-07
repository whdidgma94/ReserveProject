package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CampService {
    @Autowired
    private CampRepository campRepository;

    public Camp getCampById(Long contentId) {
        Optional<Camp> camp = campRepository.findById(contentId);
        return camp.orElse(null);
    }

}
