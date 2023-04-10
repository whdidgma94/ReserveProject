package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampService {
    private final CampRepository campRepository;
    @Autowired
    public CampService(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public void getAllCamps() {
        List<Camp> campList = campRepository.findAll();


    }
    public List<Camp> getCampListByName(String keyword){
        List<Camp> campList = campRepository.findByfacltNmContaining(keyword);
        return campList;
    }
    public List<Camp> getCampListByTheme(String keyword){
        List<Camp> campList = campRepository.findBythemaEnvrnClContaining(keyword);
        return campList;
    }
    public List<Camp> getCampListByAddress(String keyword){
        List<Camp> campList = campRepository.findByaddr1Containing(keyword);
        return campList;
    }
    public Camp getCampBycontentId(Long contentId) {
        Optional<Camp> camp = campRepository.findById(contentId);
        return camp.orElse(null);
    }

    public Camp getCampById(Long id) {
        Optional<Camp> camp = campRepository.findById(id);
        return camp.orElse(null);
    }

    public Camp selectOneById(Long id) {
        return campRepository.selectOneById(id);
    }
}
