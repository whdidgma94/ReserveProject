package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Camp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CampRepository extends JpaRepository<Camp, Long> {


    @Query("select c from Camp c where c.contentId = :contentId")
    List<Camp> selectOne(@Param("contentId") Long contentId);
    @Query("SELECT c FROM Camp c WHERE c.mapY BETWEEN :southWestLat AND :northEastLat AND c.mapX BETWEEN :southWestLng AND :northEastLng ")
    List<Camp> findCampWithinBounds(@Param("southWestLat") double southWestLat, @Param("southWestLng") double southWestLng, @Param("northEastLat") double northEastLat, @Param("northEastLng") double northEastLng);
//    @Query("SELECT c FROM Camp c WHERE c.mapY BETWEEN :southWestLat AND :northEastLat AND c.mapX BETWEEN :southWestLng AND :northEastLng AND c.facltNm LIKE %:keyword%")
//    List<Camp> findCampWithinBoundsWithName(@Param("southWestLat") double southWestLat, @Param("southWestLng") double southWestLng, @Param("northEastLat") double northEastLat, @Param("northEastLng") double northEastLng, @Param("keyword") String keyword);
//    @Query("SELECT c FROM Camp c WHERE c.mapY BETWEEN :southWestLat AND :northEastLat AND c.mapX BETWEEN :southWestLng AND :northEastLng AND c.themaEnvrnCl LIKE %:keyword%")
//    List<Camp> findCampWithinBoundsWithTheme(@Param("southWestLat") double southWestLat, @Param("southWestLng") double southWestLng, @Param("northEastLat") double northEastLat, @Param("northEastLng") double northEastLng, @Param("keyword") String keyword);
//    @Query("SELECT c FROM Camp c WHERE c.mapY BETWEEN :southWestLat AND :northEastLat AND c.mapX BETWEEN :southWestLng AND :northEastLng AND c.addr1 LIKE %:keyword%")
//    List<Camp> findCampWithinBoundsWithAddress(@Param("southWestLat") double southWestLat, @Param("southWestLng") double southWestLng, @Param("northEastLat") double northEastLat, @Param("northEastLng") double northEastLng, @Param("keyword") String keyword);

    @Query("select c from Camp c where c.id = :id")
    Camp selectOneById(@Param("id") Long id);


//    List<Camp> findByfacltNmContaining(String keyword);
//    List<Camp> findByMapXBetweenAndMapYBetweenAndFacltNmContaining(double southWestLat, double southWestLng, double northEastLat, double northEastLng, String keyword);
//    List<Camp> findBythemaEnvrnClContaining(String keyword);
//    List<Camp> findByaddr1Containing(String keyword);

    @Query("select c from Camp c where c.themaEnvrnCl = :themaEnvrnCl")
    List<Camp> selectThemaEnvrnCl(@Param("themaEnvrnCl") String themaEnvrnCl);
//    @Query("select c from Camp c where c.doNm Like %:sido% AND c.sigunguNm Like %:sigoon%")
//    List<Camp> selectListByLocation(@Param("sido") String sido,@Param("sigoon") String sigoon);


    //시,도로 필터링해서 id순 정렬된 리스트
    @Query("SELECT c FROM Camp c WHERE c.doNm LIKE %:sido% AND c.sigunguNm LIKE %:sigoon% ORDER BY c.id")
    List<Camp> selectListByLocationTest(@Param("sido") String sido, @Param("sigoon") String sigoon, @Param("pageAble") Pageable pageAble);
    //시,도로 필터링해서 id순 정렬된 리스트의 길이
    @Query("SELECT COUNT(c.id) FROM Camp c WHERE c.doNm LIKE %:sido% AND c.sigunguNm LIKE %:sigoon%")
    Long countListByLocation(@Param("sido") String sido, @Param("sigoon") String sigoon);
    @Query("SELECT c FROM Camp c WHERE c.lctCl LIKE %:lctCl%")
    List<Camp> selectListBylctCl(@Param("lctCl") String lctCl);
    @Query("SELECT c FROM Camp c WHERE c.lctCl IN (:lctCls)")
    List<Camp> selectListBylctCls(@Param("lctCls") List<String> lctCls);
    @Query("SELECT c FROM Camp c WHERE c.induty LIKE %:keyword%")
    List<Camp> selectListByInduty(@Param("keyword") String keyword);
    @Query("SELECT c FROM Camp c WHERE c.siteBottom LIKE %:keyword%")
    List<Camp> selectListByBottom(@Param("keyword") String keyword);
    @Query("SELECT c FROM Camp c WHERE c.animalCmgCl = :possible OR c.animalCmgCl = :possibleSmall")
    List<Camp> selectListByAnimal(@Param("possible") String possible, @Param("possibleSmall") String possibleSmall);
    @Query("SELECT c FROM Camp c WHERE c.caravAcmpnyAt = :Y")
    List<Camp> selectListByCaravAcmpnyAt(@Param("Y") String Y);
    @Query("SELECT c FROM Camp c WHERE c.trlerAcmpnyAt = :Y")
    List<Camp> selectListByTrlerAcmpnyAt(@Param("Y") String Y);
    @Query("SELECT c FROM Camp c WHERE c.exprnProgrmAt = :Y")
    List<Camp> selectListByExprnProgrmAt(@Param("Y") String Y);
    @Query("SELECT c FROM Camp c WHERE c.clturEventAt = :Y")
    List<Camp> selectListByClturEventAt(@Param("Y") String Y);
    @Query("SELECT c FROM Camp c WHERE c.themaEnvrnCl LIKE %:keyword%")
    List<Camp> selectListByThemaEnvrnCl(@Param("keyword") String keyword);
    @Query("SELECT c FROM Camp c WHERE c.themaEnvrnCl IN (:themas)")
    List<Camp> selectListByThemas(@Param("themas") List<String> themas);
    @Query("SELECT c FROM Camp c WHERE c.siteBottom IN (:bottoms)")
    List<Camp> selectListByBottoms(@Param("bottoms") List<String> bottoms);

}
