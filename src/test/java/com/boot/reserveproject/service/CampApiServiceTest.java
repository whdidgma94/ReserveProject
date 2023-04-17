package com.boot.reserveproject.service;

import com.boot.reserveproject.api.CampApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CampApiServiceTest {

    @Autowired
    private CampApiController api;

    @Autowired
    private CampApiService service;

//     camp API DB에 넣을때 사용했음
    @Test
    public void addData() {
        int minPageNum = (1089); // 1089
        int maxPageNum = (3436);
        for (int i = minPageNum; i <= maxPageNum; i++) {
            service.saveCamps(api.getBasedList(String.valueOf(i)));
            System.out.println("진행페이지 = " + i + "/" +maxPageNum);
        }
    }

    //테마종류 확인



}