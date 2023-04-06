package com.boot.reserveproject.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class CampApiController {
    public List<HashMap<String, Object>> getBasedList(String pageNum) {
        String base_url = "https://apis.data.go.kr/B551011/GoCamping/";
        String subject = "basedList";
        String page = "?pageNo="+pageNum+"&MobileOS=ETC&MobileApp=%EC%97%AC%EA%B8%B0%EC%99%80%EC%9D%B4";
        String api_key = "&serviceKey=qm8FKb8D52kHWNgstNLDyuuLD%2Bh2H8dvHvULfHsDN23RLMAPmvGDcv%2BG7P%2B5fAEonCEHTQTl%2F1X2WJvP9IUM4Q%3D%3D&_type=json";

        String apiUrl = base_url + subject + page + api_key;

        List<HashMap<String, Object>> basedList = null;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            System.out.println(1);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
            System.out.println(sb);
            JSONObject obj = new JSONObject(sb.toString());
            JSONObject obj2 = (JSONObject) obj.get("response");
            System.out.println("obj2 = " + obj2);
            JSONObject obj3 = (JSONObject) obj2.get("body");
            System.out.println("obj3 = " + obj3);
            JSONObject obj4 = (JSONObject) obj3.get("items");
            System.out.println("obj4 = " + obj4);
            JSONArray objArray = obj4.getJSONArray("item");
            System.out.println("objArray = " + objArray);
            JSONObject jb = (JSONObject) objArray.get(0);
            System.out.println(jb.get("contentId"));
//            basedList = new ArrayList<HashMap<String, Object>>();
//
//            for (int i = 0; i < objArray.length(); i++) {
//                HashMap<String, Object> basedMap = new HashMap<>();
//                JSONObject jb = (JSONObject) objArray.get(i);
//                if (!jb.get("overview").equals("")) {
//
//                    basedMap.put("contentId", jb.get("contentId"));
//                    basedMap.put("facltNm", jb.get("facltNm"));
//                    basedMap.put("lineIntro", jb.get("lineIntro"));
//                    basedMap.put("intro", jb.get("intro"));
//                    basedMap.put("allar", jb.get("allar"));
//                    basedMap.put("mgcDiv", jb.get("mgcDiv"));
//                    basedMap.put("manageSttus", jb.get("manageSttus"));
//
//                    basedList.add(basedMap);
//                }
//            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return basedList;

    }
}
