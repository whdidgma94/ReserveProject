package com.boot.reserveproject.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CampApiController {
    public List<HashMap<String, Object>> getBasedList(String pageNum) {
        String base_url = "https://apis.data.go.kr/B551011/GoCamping/";
        String subject = "basedList";
        String page = "?numOfRows=2&pageNo="+pageNum+"&MobileOS=ETC&MobileApp=%EC%97%AC%EA%B8%B0%EC%99%80%EC%9D%B4";
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
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject obj = new JSONObject(sb.toString());
            JSONObject obj2 = obj.getJSONObject("response").getJSONObject("body");
            JSONArray objArray = obj2.getJSONObject("items").getJSONArray("item");

            basedList = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < objArray.length(); i++) {
                HashMap<String, Object> basedMap = new HashMap<>();
                JSONObject jb = (JSONObject) objArray.get(i);

                    basedMap.put("contentId", jb.get("contentId"));
                    basedMap.put("facltNm", jb.get("facltNm"));
//                    basedMap.put("lineIntro", jb.get("lineIntro"));
//                    basedMap.put("intro", jb.get("intro"));
                    basedMap.put("allar", jb.get("allar"));
                    basedMap.put("facltDivNm", jb.get("facltDivNm"));
                    basedMap.put("manageSttus", jb.get("manageSttus"));

                    basedMap.put("sitedStnc", jb.get("sitedStnc"));
                    basedMap.put("siteMg1Width", jb.get("siteMg1Width"));
                    basedMap.put("siteMg1Vrticl", jb.get("siteMg1Vrticl"));
                    basedMap.put("siteMg1Co", jb.get("siteMg1Co"));
                    basedMap.put("siteMg2Width", jb.get("siteMg2Width"));
                    basedMap.put("siteMg2Vrticl", jb.get("siteMg2Vrticl"));
                    basedMap.put("siteMg2Co", jb.get("siteMg2Co"));
                    basedMap.put("siteMg3Width", jb.get("siteMg3Width"));
                    basedMap.put("siteMg3Vrticl", jb.get("siteMg3Vrticl"));
                    basedMap.put("siteMg3Co", jb.get("siteMg3Co"));

                    basedMap.put("siteBottomCl1", jb.get("siteBottomCl1"));
                    basedMap.put("siteBottomCl2", jb.get("siteBottomCl2"));
                    basedMap.put("siteBottomCl3", jb.get("siteBottomCl3"));
                    basedMap.put("siteBottomCl4", jb.get("siteBottomCl4"));
                    basedMap.put("siteBottomCl5", jb.get("siteBottomCl5"));








                    basedList.add(basedMap);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return basedList;

    }
}
