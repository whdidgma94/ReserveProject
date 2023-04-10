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

    public List<Camp> getCampListByName(Double southWestLat,Double southWestLng,Double northEastLat,Double northEastLng,String keyword){
//        List<Camp> campList = campRepository.findByfacltNmContaining(keyword);
//        return campList;
        List<Camp> campList = campRepository.findCampWithinBoundsWithName(southWestLat,southWestLng,northEastLat,northEastLng,keyword);
        return campList;

    }
    public List<Camp> getCampListByTheme(Double southWestLat,Double southWestLng,Double northEastLat,Double northEastLng,String keyword){
        List<Camp> campList = campRepository.findCampWithinBoundsWithTheme(southWestLat,southWestLng,northEastLat,northEastLng,keyword);
        return campList;
    }
    public List<Camp> getCampListByAddress(Double southWestLat,Double southWestLng,Double northEastLat,Double northEastLng,String keyword){
        List<Camp> campList = campRepository.findCampWithinBoundsWithAddress(southWestLat,southWestLng,northEastLat,northEastLng,keyword);
        return campList;
    }
    public Camp getCampById(Long contentId) {
        Optional<Camp> camp = campRepository.findById(contentId);
        return camp.orElse(null);
    }


}
