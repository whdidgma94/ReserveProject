package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Component
public interface CampRepository extends JpaRepository<Camp, Long> {
    @Query("select c from Camp c where c.contentId = :contentId")
    List<Camp> selectOne(@Param("contentId") Long contentId);
}
