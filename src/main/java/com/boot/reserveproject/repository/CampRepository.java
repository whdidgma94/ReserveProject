package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
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
    @Query("SELECT c FROM Camp c WHERE c.mapY BETWEEN :southWestLat AND :northEastLat AND c.mapX BETWEEN :southWestLng AND :northEastLng AND c.facltNm LIKE %:keyword%")
    List<Camp> findCampWithinBoundsWithName(@Param("southWestLat") double southWestLat, @Param("southWestLng") double southWestLng, @Param("northEastLat") double northEastLat, @Param("northEastLng") double northEastLng, @Param("keyword") String keyword);
    @Query("SELECT c FROM Camp c WHERE c.mapY BETWEEN :southWestLat AND :northEastLat AND c.mapX BETWEEN :southWestLng AND :northEastLng AND c.themaEnvrnCl LIKE %:keyword%")
    List<Camp> findCampWithinBoundsWithTheme(@Param("southWestLat") double southWestLat, @Param("southWestLng") double southWestLng, @Param("northEastLat") double northEastLat, @Param("northEastLng") double northEastLng, @Param("keyword") String keyword);
    @Query("SELECT c FROM Camp c WHERE c.mapY BETWEEN :southWestLat AND :northEastLat AND c.mapX BETWEEN :southWestLng AND :northEastLng AND c.addr1 LIKE %:keyword%")
    List<Camp> findCampWithinBoundsWithAddress(@Param("southWestLat") double southWestLat, @Param("southWestLng") double southWestLng, @Param("northEastLat") double northEastLat, @Param("northEastLng") double northEastLng, @Param("keyword") String keyword);

    @Query("select c from Camp c where c.id = :id")
    Camp selectOneById(@Param("id") Long id);


//    List<Camp> findByfacltNmContaining(String keyword);
//    List<Camp> findByMapXBetweenAndMapYBetweenAndFacltNmContaining(double southWestLat, double southWestLng, double northEastLat, double northEastLng, String keyword);
//    List<Camp> findBythemaEnvrnClContaining(String keyword);
//    List<Camp> findByaddr1Containing(String keyword);

    @Query("select c from Camp c where c.themaEnvrnCl = :themaEnvrnCl")
    List<Camp> selectThemaEnvrnCl(@Param("themaEnvrnCl") String themaEnvrnCl);
    @Query("select c from Camp c where c.doNm Like %:sido% AND c.sigunguNm Like %:sigoon%")
    List<Camp> selectListByLocation(@Param("sido") String sido,@Param("sigoon") String sigoon);

//    @Query("SELECT c.lctCl FROM Camp c")
//    List<String> findAllThema();
//
//    @Query("SELECT c FROM Camp c WHERE c.lctCl = :thema")
//    Camp findByThema(@Param("thema") String thema);
//
//    @Query("SELECT c FROM Camp c WHERE c.lctCl LIKE %:keyword%")
//    List<Camp> findByLocation(@Param("keyword") String keyword);
}
