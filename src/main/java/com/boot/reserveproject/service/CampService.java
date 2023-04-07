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
    public Optional<Camp> getOneCamp(Long id) {
        return campRepository.findById(id);
    }
    public List<Camp> getCampListByName(String keyword){
        List<Camp> campList = campRepository.findByfacltNmContaining(keyword);
        return campList;
    }


}
