package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.repository.CampRepository;
import com.boot.reserveproject.repository.MemberLikesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampService {
    private final CampRepository campRepository;
    private final MemberLikesRepository memberLikesRepository;
    @Autowired
    public CampService(CampRepository campRepository, MemberLikesRepository memberLikesRepository) {
        this.campRepository = campRepository;
        this.memberLikesRepository = memberLikesRepository;
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
    public List<Camp> findByClturEventAt(){
        List<Camp> campList = campRepository.selectListByClturEventAt("Y");
        return campList;
    }
    public List<Camp> findByExprnProgrmAt(){
        List<Camp> campList = campRepository.selectListByExprnProgrmAt("Y");
        return campList;
    }
    public List<Camp> findByTrlerAcmpnyAt(){
        List<Camp> campList = campRepository.selectListByTrlerAcmpnyAt("Y");
        return campList;
    }
    public List<Camp> findByCaravAcmpnyAt(){
        List<Camp> campList = campRepository.selectListByCaravAcmpnyAt("Y");
        return campList;
    }
    public List<Camp> findByInduty(String keyword){
        List<Camp> campList = campRepository.selectListByInduty(keyword);
        return campList;
    }
    public List<Camp> findByThemas(List<String> themas){
        List<Camp> campList = campRepository.selectListByThemas(themas);
        return campList;
    }
    public List<Camp> findByThema(String keyword){
        List<Camp> campList = campRepository.selectListByThemaEnvrnCl(keyword);
        return campList;
    }
    public List<Camp> findByLctCls(List<String> lctCls){
        List<Camp> campList = campRepository.selectListBylctCls(lctCls);
        return campList;
    }
    public List<Camp> findByBottoms(List<String> bottoms){
        List<Camp> campList = campRepository.selectListByBottoms(bottoms);
        return campList;
    }
    public List<Camp> findByBottom(String keyword){
        List<Camp> campList = campRepository.selectListByBottom(keyword);
        return campList;
    }
    public List<Camp> findAllList(){
        List<Camp> campList = campRepository.findAll();
        return campList;
    }

    public List<Camp> selectMemberListByLoginId(String loginId){
        List<Long> contentIdList = memberLikesRepository.selectMemberListByLoginId(loginId);
        List<Camp> memberLikesList = new ArrayList<>();
        for(int i=0;i<contentIdList.size();i++){
            memberLikesList.add(campRepository.selectOneById(contentIdList.get(i)));
        }
        return memberLikesList;
    }
}
