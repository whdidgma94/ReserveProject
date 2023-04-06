package com.boot.reserveproject.api;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public
class CampApiControllerTest {

    @Test
    public void test() {
        CampApiController api = new CampApiController();
        List<HashMap<String, Object>> basedList = api.getBasedList("1");

        System.out.println("Based List:");
        for (HashMap<String, Object> basedMap : basedList) {
            System.out.println(basedMap);
        }

        for (HashMap<String, Object> basedMap : basedList) {
            Collection<Object> values = basedMap.values();
            for (Object value : values) {
                System.out.println(value);
            }
            System.out.println(); // 개행
        }

    }
}