package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
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
    public List<Camp> getCampListByBounds(Double southWestLat,Double southWestLng,Double northEastLat,Double northEastLng){

        List<Camp> campList = campRepository.findCampWithinBounds(southWestLat,southWestLng,northEastLat,northEastLng);
        return campList;

    }

//    public List<Camp> getCampListByName(Double southWestLat,Double southWestLng,Double northEastLat,Double northEastLng,String keyword){
////        List<Camp> campList = campRepository.findByfacltNmContaining(keyword);
////        return campList;
//        List<Camp> campList = campRepository.findCampWithinBoundsWithName(southWestLat,southWestLng,northEastLat,northEastLng,keyword);
//        return campList;
//
//    }
//    public List<Camp> getCampListByTheme(Double southWestLat,Double southWestLng,Double northEastLat,Double northEastLng,String keyword){
//        List<Camp> campList = campRepository.findCampWithinBoundsWithTheme(southWestLat,southWestLng,northEastLat,northEastLng,keyword);
//        return campList;
//    }
//    public List<Camp> getCampListByAddress(Double southWestLat,Double southWestLng,Double northEastLat,Double northEastLng,String keyword){
//        List<Camp> campList = campRepository.findCampWithinBoundsWithAddress(southWestLat,southWestLng,northEastLat,northEastLng,keyword);
//        return campList;
//    }
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
//    public List<Camp> selectListByLocation(String sido,String sigoon){
//        List<Camp> campList = campRepository.selectListByLocation(sido,sigoon);
//        return campList;
//    }
    public List<Camp> findByDoNmContainingAndSigunguNmContainingOrderById(String sido, String sigoon, Pageable pageAble){
        List<Camp> campList = campRepository.selectListByLocationTest(sido,sigoon,pageAble);
        return campList;
    }
    public Long countListByLocation(String sido,String sigoon){
        Long length= campRepository.countListByLocation(sido,sigoon);
        return length;
    }
    public List<Camp> findBylctCl(String lctCl){
        List<Camp> campList = campRepository.selectListBylctCl(lctCl);
        return campList;
    }
    public List<Camp> findByAnimal(){
        List<Camp> campList = campRepository.selectListByAnimal("가능", "가능(소형견)");
        return campList;
    }
    public List<Camp> findByclturEventAt(){
        List<Camp> campList = campRepository.selectListByClturEventAt("Y");
        return campList;
    }
}
