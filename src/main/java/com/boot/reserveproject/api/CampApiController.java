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
        String page = "?numOfRows=2&pageNo=" + pageNum + "&MobileOS=ETC&MobileApp=%EC%97%AC%EA%B8%B0%EC%99%80%EC%9D%B4";
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

            //api분해
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


                //주요시설
                String siteCount = "";
                String[] type = {"","일반야영장", "자동차야영장", "글램핑시설", "카라반", "개인카라반"};
                String[] apiTypeName = {"","gnrl", "auto", "glamp", "carav", "indvdl"};
                for (int j = 1; j <= 5; j++) {
                    String siteBottoms = jb.optString(apiTypeName[j]+"SiteCo", "0");
                    if (!siteBottoms.equals("0")) {
                        String temp = type[j] +":"+ siteBottoms+"면";
                        siteCount = siteCount + temp + " ";
                    }
                }
                basedMap.put("siteCount", siteCount);

                //바닥크기
                String site = "";
                for (int j = 1; j <= 3; j++) {
                    String siteWidth = jb.optString("siteMg" + j + "Width", "0");
                    if (!siteWidth.equals("0")) {
                        String temp = siteWidth + "X" +
                                jb.get("siteMg" + j + "Vrticl") +
                                ":" + jb.get("siteMg" + j + "Co") + "개";
                        site = site + temp + " ";
                    }
                }
                basedMap.put("site", site);

                //바닥형태
                String siteBottom = "";
                String[] materials = {"","잔디", "파쇄석", "데크", "자갈", "맨흙"};
                for (int j = 1; j <= 5; j++) {
                    String siteBottoms = jb.optString("siteBottomCl" + j, "0");
                    if (!siteBottoms.equals("0")) {
                        String temp = materials[j] +":"+ siteBottoms+"면";
                        siteBottom = siteBottom + temp + " ";
                    }
                }
                basedMap.put("siteBottom", siteBottom);

                basedMap.put("glampInnerFclty", jb.get("glampInnerFclty"));


                basedList.add(basedMap);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return basedList;

    }
}
