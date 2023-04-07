package com.boot.reserveproject.api;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public
class CampApiControllerTest {

    @Test
    public void test() {
        CampApiController api = new CampApiController();
        List<HashMap<String, Object>> basedList = api.getBasedList("3175");

        System.out.println(" -- Based List1 :");
        for (HashMap<String, Object> basedMap : basedList) {
            System.out.println(basedMap);
        }

        System.out.println("\n -- Based List2");
        for (HashMap<String, Object> basedMap : basedList) {
            for (Map.Entry<String, Object> entry : basedMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println(key + " : " + value);
            }
            System.out.println(); // 개행
        }
    }

    @Test
    public void test2() {
        CampApiController api = new CampApiController();
        String[] imageList = api.getImageList(4L);
        System.out.println("\n -- image List");
        for (String image : imageList) {
            System.out.println(image);
        }
    }


}