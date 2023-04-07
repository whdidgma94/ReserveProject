package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.service.CampApiService;
import com.boot.reserveproject.service.MemberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampApiRepository extends JpaRepository<Camp,Long> {



}
