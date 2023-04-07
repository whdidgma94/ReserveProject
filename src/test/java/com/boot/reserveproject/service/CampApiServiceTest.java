package com.boot.reserveproject.service;

import com.boot.reserveproject.api.CampApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CampApiServiceTest {

    @Autowired
    private CampApiController api;

    @Autowired
    private CampApiService service;

    @Test
    public void addData() {
        int minPageNum = (3176);
        int maxPageNum = (3436);
        for (int i = minPageNum; i <= maxPageNum; i++) {
            service.saveCamps(api.getBasedList(String.valueOf(i)));
            System.out.println("진행페이지 = " + i + "/" +maxPageNum);
        }
    }
}