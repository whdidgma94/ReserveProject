package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Camp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CampServiceTest {

    @Autowired
    private CampService service;

    @Test
    public void categoryCheck() {
        Set<String> categoryList = new HashSet<>();
        for (int i = 1; i < 50; i++) {
            Camp campTemp = service.getCampById((long) i);
            if(campTemp == null){
                continue;
            }
            String textTemp = campTemp.getThemaEnvrnCl();
            String[] arrTemp = textTemp.split(",");
            System.out.println("arrTemp = " + Arrays.toString(arrTemp));
            for (int j = 0; j < arrTemp.length; j++) { // 조건식 수정
                if (!categoryList.contains(arrTemp[j])) {
                    categoryList.add(arrTemp[j]);
                }
            }
        }
        String[] result = new String[categoryList.size()];
        int index = 0;
        for (String str : categoryList) {
            result[index++] = str;
        }
        System.out.println(Arrays.toString(result));
    }
}