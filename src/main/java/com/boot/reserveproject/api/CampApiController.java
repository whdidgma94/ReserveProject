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
                basedMap.put("hvofBgnde", jb.get("hvofBgnde"));
                basedMap.put("hvofEnddle", jb.get("hvofEnddle"));
                basedMap.put("featureNm", jb.get("featureNm"));
                basedMap.put("induty", jb.get("induty"));
                basedMap.put("lctCl", jb.get("lctCl"));

                //주소
                basedMap.put("doNm", jb.get("doNm"));
                basedMap.put("sigunguNm", jb.get("sigunguNm"));
                basedMap.put("zipcode", jb.get("zipcode"));
                basedMap.put("addr1", jb.get("addr1"));
                basedMap.put("addr2", jb.get("addr2"));
                //위도경도
                basedMap.put("mapX", jb.get("mapX"));
                basedMap.put("mapY", jb.get("mapY"));

                basedMap.put("direction", jb.get("direction"));
                basedMap.put("tel", jb.get("tel"));
                basedMap.put("homepage", jb.get("homepage"));

                basedMap.put("mapY", jb.get("mapY"));

                //주요시설
                String siteCount = "";
                String[] type = {"", "일반야영장", "자동차야영장", "글램핑시설", "카라반", "개인카라반"};
                String[] apiTypeName = {"", "gnrl", "auto", "glamp", "carav", "indvdl"};
                for (int j = 1; j <= 5; j++) {
                    String siteBottoms = jb.optString(apiTypeName[j] + "SiteCo", "0");
                    if (!siteBottoms.equals("0")) {
                        String temp = type[j] + ":" + siteBottoms + "면";
                        siteCount += temp + " ";
                    }
                }
                basedMap.put("siteCount", siteCount.trim());

                basedMap.put("sitedStnc", jb.get("sitedStnc"));

                //바닥크기
                String site = "";
                for (int j = 1; j <= 3; j++) {
                    String siteWidth = jb.optString("siteMg" + j + "Width", "0");
                    if (!siteWidth.equals("0")) {
                        String temp = siteWidth + "X" +
                                jb.get("siteMg" + j + "Vrticl") +
                                ":" + jb.get("siteMg" + j + "Co") + "개";
                        site += temp + " ";
                    }
                }
                basedMap.put("site", site.trim());

                //바닥형태
                String siteBottom = "";
                String[] materials = {"", "잔디", "파쇄석", "데크", "자갈", "맨흙"};
                for (int j = 1; j <= 5; j++) {
                    String siteBottoms = jb.optString("siteBottomCl" + j, "0");
                    if (!siteBottoms.equals("0")) {
                        String temp = materials[j] + ":" + siteBottoms + "면";
                        siteBottom += temp + " ";
                    }
                }
                basedMap.put("siteBottom", siteBottom.trim());

                basedMap.put("tooltip", jb.get("tooltip"));

                basedMap.put("glampInnerFclty", jb.get("glampInnerFclty"));
                basedMap.put("caravInnerFclty", jb.get("caravInnerFclty"));

                basedMap.put("operPdCl", jb.get("operPdCl"));
                basedMap.put("operDeCl", jb.get("operDeCl"));
                basedMap.put("trlerAcmpnyAt", jb.get("trlerAcmpnyAt"));
                basedMap.put("caravAcmpnyAt", jb.get("caravAcmpnyAt"));

                //위생시설, (부대시설)
                String sanitary = "";
                if (!jb.get("toiletCo").equals("0")) {
                    sanitary += "화장실:" + jb.get("toiletCo") + "개 ";
                }
                if (!jb.get("swrmCo").equals("0")) {
                    sanitary += "샤워실:" + jb.get("swrmCo") + "개 ";
                }
                if (!jb.get("wtrplCo").equals("0")) {
                    sanitary += "개수대:" + jb.get("wtrplCo") + "개 ";
                }
                basedMap.put("sanitary", sanitary.trim());

                basedMap.put("sbrsCl", jb.get("sbrsCl"));

                basedMap.put("sbrsEtc", jb.get("sbrsEtc"));

                //안전시설현황, 소방시설개수
                String fpSystem = "";
                if (!jb.get("extshrCo").equals("0")) {
                    fpSystem += "소화기:" + jb.get("extshrCo") + "개 ";
                }
                if (!jb.get("frprvtWrppCo").equals("0")) {
                    fpSystem += "방화수:" + jb.get("frprvtWrppCo") + "개 ";
                }
                if (!jb.get("frprvtSandCo").equals("0")) {
                    fpSystem += "방화사:" + jb.get("frprvtSandCo") + "개 ";
                }
                if (!jb.get("fireSensorCo").equals("0")) {
                    fpSystem += "화재감지기:" + jb.get("fireSensorCo") + "개 ";
                }
                basedMap.put("fpSystem", fpSystem.trim());

                basedMap.put("posblFcltyCl", jb.get("posblFcltyCl"));
                basedMap.put("posblFcltyEtc", jb.get("posblFcltyEtc"));
                basedMap.put("clturEventAt", jb.get("clturEventAt"));
                basedMap.put("clturEvent", jb.get("clturEvent"));
                basedMap.put("exprnProgrmAt", jb.get("exprnProgrmAt"));
                basedMap.put("exprnProgrm", jb.get("exprnProgrm"));

                basedMap.put("themaEnvrnCl", jb.get("themaEnvrnCl"));
                basedMap.put("animalCmgCl", jb.get("animalCmgCl"));
                basedMap.put("firstImageUrl", jb.get("firstImageUrl"));

                basedList.add(basedMap);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basedList;
    }


    //contentId로 이미지 불러오기
    public String[] getImageList(Long contentId) {
        String base_url = "https://apis.data.go.kr/B551011/GoCamping/";
        String subject = "imageList";
        String api_key = "?MobileOS=WIN&MobileApp=wwwadf&serviceKey=qm8FKb8D52kHWNgstNLDyuuLD%2Bh2H8dvHvULfHsDN23RLMAPmvGDcv%2BG7P%2B5fAEonCEHTQTl%2F1X2WJvP9IUM4Q%3D%3D&_type=json";
        String contentIdNum = "&contentId=" + contentId;

        String apiUrl = base_url + subject + api_key + contentIdNum;

        String[] imageList = null;

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

            imageList = new String[objArray.length()];
            for (int i = 0; i < objArray.length(); i++) {
                JSONObject jb = (JSONObject) objArray.get(i);
                imageList[i] = jb.getString("imageUrl");
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageList;
    }
}
