package com.boot.reserveproject.api;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public
class CampApiControllerTest {

    @Test
    public void test() {
        CampApiController api = new CampApiController();
        List<HashMap<String, Object>> basedList = api.getBasedList("1");

        System.out.println(basedList);
    }
}