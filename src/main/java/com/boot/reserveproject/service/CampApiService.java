package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.repository.CampApiRepository;
import com.boot.reserveproject.repository.CampRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CampApiService {

    @Autowired
    private CampApiRepository campApiRepository;
    private final CampRepository campRepository;

    public CampApiService(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public void saveCamps(List<HashMap<String, Object>> besedList) {
        RestTemplate restTemplate = new RestTemplate();

        for (HashMap<String, Object> camps : besedList) {
            Camp camp = new Camp();
            camp.setContentId(Long.parseLong((String) camps.get("contentId")));
            camp.setFacltNm((String) camps.get("facltNm"));
            camp.setLineIntro((String) camps.get("lineIntro"));
            camp.setLineIntro((String) camps.get("lineIntro"));
            camp.setIntro((String) camps.get("intro"));

            camp.setFacltDivNm((String) camps.get("facltDivNm"));
            camp.setManageSttus((String) camps.get("manageSttus"));
            camp.setHvofBgnde((String) camps.get("hvofBgnde"));
            camp.setHvofEnddle((String) camps.get("hvofEnddle"));
            camp.setHvofEnddle((String) camps.get("hvofEnddle"));
            camp.setInduty((String) camps.get("induty"));
            camp.setLctCl((String) camps.get("lctCl"));

            camp.setDoNm((String) camps.get("doNm"));
            camp.setSigunguNm((String) camps.get("sigunguNm"));
            camp.setZipcode((String) camps.get("zipcode"));
            camp.setAddr1((String) camps.get("addr1"));
            camp.setAddr2((String) camps.get("addr2"));

            camp.setMapX(Double.parseDouble((String) camps.get("mapX")));
            camp.setMapY(Double.parseDouble((String) camps.get("mapY")));

            camp.setDirection((String) camps.get("direction"));
            camp.setTel((String) camps.get("tel"));
            camp.setHomepage((String) camps.get("homepage"));

            camp.setSiteCount((String) camps.get("siteCount"));
            camp.setSitedStnc(Long.parseLong((String) camps.get("sitedStnc")));
            camp.setSite((String) camps.get("site"));

            camp.setSiteBottom((String) camps.get("siteBottom"));

            camp.setTooltip((String) camps.get("tooltip"));

            camp.setGlampInnerFclty((String) camps.get("glampInnerFclty"));
            camp.setCaravInnerFclty((String) camps.get("caravInnerFclty"));

            camp.setOperPdCl((String) camps.get("operPdCl"));
            camp.setOperDeCl((String) camps.get("operDeCl"));
            camp.setTrlerAcmpnyAt((String) camps.get("trlerAcmpnyAt"));
            camp.setCaravAcmpnyAt((String) camps.get("caravAcmpnyAt"));

            camp.setSanitary((String) camps.get("sanitary"));

            camp.setBrazierCl((String) camps.get("brazierCl"));
            camp.setSbrsCl((String) camps.get("sbrsCl"));
            camp.setSbrsEtc((String) camps.get("sbrsEtc"));

            camp.setFpSystem((String) camps.get("fpSystem"));

            camp.setPosblFcltyCl((String) camps.get("posblFcltyCl"));
            camp.setPosblFcltyEtc((String) camps.get("posblFcltyEtc"));
            camp.setClturEventAt((String) camps.get("clturEventAt"));
            camp.setClturEvent((String) camps.get("clturEvent"));
            camp.setExprnProgrmAt((String) camps.get("exprnProgrmAt"));
            camp.setExprnProgrm((String) camps.get("exprnProgrm"));

            camp.setThemaEnvrnCl((String) camps.get("themaEnvrnCl"));
            camp.setAnimalCmgCl((String) camps.get("animalCmgCl"));
            camp.setFirstImageUrl((String) camps.get("firstImageUrl"));

            campApiRepository.save(camp);
        }
    }

    public void updateApi(int getDate) {
        String base_url = "https://apis.data.go.kr/B551011/GoCamping/";
        String subject = "basedSyncList";
        String page = "?MobileOS=ETC&MobileApp=welcomeY";
        String api_key = "&serviceKey=qm8FKb8D52kHWNgstNLDyuuLD%2Bh2H8dvHvULfHsDN23RLMAPmvGDcv%2BG7P%2B5fAEonCEHTQTl%2F1X2WJvP9IUM4Q%3D%3D";
        String date = "&yncModTime=" + getDate;

        String apiUrl = base_url + subject + page + api_key + date;
        List<HashMap<String, Object>> basedSyncList = null;

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
            System.out.println(sb.toString());
            JSONObject obj = new JSONObject(sb.toString());
            JSONObject obj2 = obj.getJSONObject("response").getJSONObject("body");
            JSONArray objArray = obj2.getJSONObject("items").getJSONArray("item");

            //api분해
            basedSyncList = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < objArray.length(); i++) {
                HashMap<String, Object> basedMap = new HashMap<>();
                JSONObject jb = (JSONObject) objArray.get(i);
                Long contentId = Long.parseLong(jb.get("contentId").toString());
                if (jb.get("syncStatus").toString().equals("D")) {
                    campRepository.deleteById(contentId);
                    System.out.println("D");
                } else if (jb.get("syncStatus").toString().equals("U")) {
                    campRepository.deleteById(contentId);
                    Camp camp = new Camp();
                    camp.setContentId(Long.parseLong(jb.get("contentId").toString()));
                    camp.setFacltNm(jb.get("facltNm").toString());
                    camp.setLineIntro(jb.get("lineIntro").toString());
                    camp.setLineIntro(jb.get("lineIntro").toString());
                    camp.setIntro(jb.get("intro").toString());

                    camp.setFacltDivNm(jb.get("facltDivNm").toString());
                    camp.setManageSttus(jb.get("manageSttus").toString());
                    camp.setHvofBgnde(jb.get("hvofBgnde").toString());
                    camp.setHvofEnddle(jb.get("hvofEnddle").toString());
                    camp.setHvofEnddle(jb.get("hvofEnddle").toString());
                    camp.setInduty(jb.get("induty").toString());
                    camp.setLctCl(jb.get("lctCl").toString());

                    camp.setDoNm(jb.get("doNm").toString());
                    camp.setSigunguNm(jb.get("sigunguNm").toString());
                    camp.setZipcode(jb.get("zipcode").toString());
                    camp.setAddr1(jb.get("addr1").toString());
                    camp.setAddr2(jb.get("addr2").toString());

                    camp.setMapX(Double.parseDouble(jb.get("mapX").toString()));
                    camp.setMapY(Double.parseDouble(jb.get("mapY").toString()));

                    camp.setDirection(jb.get("direction").toString());
                    camp.setTel(jb.get("tel").toString());
                    camp.setHomepage(jb.get("homepage").toString());
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
                    camp.setSiteCount(siteCount.trim());
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

                    camp.setSitedStnc(Long.parseLong(jb.get("sitedStnc").toString()));
                    camp.setSite(site.trim());
                    String siteBottom = "";
                    String[] materials = {"", "잔디", "파쇄석", "데크", "자갈", "맨흙"};
                    for (int j = 1; j <= 5; j++) {
                        String siteBottoms = jb.optString("siteBottomCl" + j, "0");
                        if (!siteBottoms.equals("0")) {
                            String temp = materials[j] + ":" + siteBottoms + "면";
                            siteBottom += temp + " ";
                        }
                    }

                    camp.setSiteBottom(siteBottom.trim());

                    camp.setTooltip(jb.get("tooltip").toString());

                    camp.setGlampInnerFclty(jb.get("glampInnerFclty").toString());
                    camp.setCaravInnerFclty(jb.get("caravInnerFclty").toString());

                    camp.setOperPdCl(jb.get("operPdCl").toString());
                    camp.setOperDeCl(jb.get("operDeCl").toString());
                    camp.setTrlerAcmpnyAt(jb.get("trlerAcmpnyAt").toString());
                    camp.setCaravAcmpnyAt(jb.get("caravAcmpnyAt").toString());
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
                    camp.setSanitary(sanitary.trim());

                    camp.setBrazierCl(jb.get("brazierCl").toString());
                    camp.setSbrsCl(jb.get("sbrsCl").toString());
                    camp.setSbrsEtc(jb.get("sbrsEtc").toString());
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
                    camp.setFpSystem(fpSystem.trim());

                    camp.setPosblFcltyCl(jb.get("posblFcltyCl").toString());
                    camp.setPosblFcltyEtc(jb.get("posblFcltyEtc").toString());
                    camp.setClturEventAt(jb.get("clturEventAt").toString());
                    camp.setClturEvent(jb.get("clturEvent").toString());
                    camp.setExprnProgrmAt(jb.get("exprnProgrmAt").toString());
                    camp.setExprnProgrm(jb.get("exprnProgrm").toString());

                    camp.setThemaEnvrnCl(jb.get("themaEnvrnCl").toString());
                    camp.setAnimalCmgCl(jb.get("animalCmgCl").toString());
                    camp.setFirstImageUrl(jb.get("firstImageUrl").toString());

                    campApiRepository.save(camp);
                    System.out.println("U");
                } else {
                    Camp camp = new Camp();
                    camp.setContentId(Long.parseLong(jb.get("contentId").toString()));
                    camp.setFacltNm(jb.get("facltNm").toString());
                    camp.setLineIntro(jb.get("lineIntro").toString());
                    camp.setLineIntro(jb.get("lineIntro").toString());
                    camp.setIntro(jb.get("intro").toString());

                    camp.setFacltDivNm(jb.get("facltDivNm").toString());
                    camp.setManageSttus(jb.get("manageSttus").toString());
                    camp.setHvofBgnde(jb.get("hvofBgnde").toString());
                    camp.setHvofEnddle(jb.get("hvofEnddle").toString());
                    camp.setHvofEnddle(jb.get("hvofEnddle").toString());
                    camp.setInduty(jb.get("induty").toString());
                    camp.setLctCl(jb.get("lctCl").toString());

                    camp.setDoNm(jb.get("doNm").toString());
                    camp.setSigunguNm(jb.get("sigunguNm").toString());
                    camp.setZipcode(jb.get("zipcode").toString());
                    camp.setAddr1(jb.get("addr1").toString());
                    camp.setAddr2(jb.get("addr2").toString());

                    camp.setMapX(Double.parseDouble(jb.get("mapX").toString()));
                    camp.setMapY(Double.parseDouble(jb.get("mapY").toString()));

                    camp.setDirection(jb.get("direction").toString());
                    camp.setTel(jb.get("tel").toString());
                    camp.setHomepage(jb.get("homepage").toString());
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
                    camp.setSiteCount(siteCount.trim());
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

                    camp.setSitedStnc(Long.parseLong(jb.get("sitedStnc").toString()));
                    camp.setSite(site.trim());
                    String siteBottom = "";
                    String[] materials = {"", "잔디", "파쇄석", "데크", "자갈", "맨흙"};
                    for (int j = 1; j <= 5; j++) {
                        String siteBottoms = jb.optString("siteBottomCl" + j, "0");
                        if (!siteBottoms.equals("0")) {
                            String temp = materials[j] + ":" + siteBottoms + "면";
                            siteBottom += temp + " ";
                        }
                    }

                    camp.setSiteBottom(siteBottom.trim());

                    camp.setTooltip(jb.get("tooltip").toString());

                    camp.setGlampInnerFclty(jb.get("glampInnerFclty").toString());
                    camp.setCaravInnerFclty(jb.get("caravInnerFclty").toString());

                    camp.setOperPdCl(jb.get("operPdCl").toString());
                    camp.setOperDeCl(jb.get("operDeCl").toString());
                    camp.setTrlerAcmpnyAt(jb.get("trlerAcmpnyAt").toString());
                    camp.setCaravAcmpnyAt(jb.get("caravAcmpnyAt").toString());
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
                    camp.setSanitary(sanitary.trim());

                    camp.setBrazierCl(jb.get("brazierCl").toString());
                    camp.setSbrsCl(jb.get("sbrsCl").toString());
                    camp.setSbrsEtc(jb.get("sbrsEtc").toString());
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
                    camp.setFpSystem(fpSystem.trim());

                    camp.setPosblFcltyCl(jb.get("posblFcltyCl").toString());
                    camp.setPosblFcltyEtc(jb.get("posblFcltyEtc").toString());
                    camp.setClturEventAt(jb.get("clturEventAt").toString());
                    camp.setClturEvent(jb.get("clturEvent").toString());
                    camp.setExprnProgrmAt(jb.get("exprnProgrmAt").toString());
                    camp.setExprnProgrm(jb.get("exprnProgrm").toString());

                    camp.setThemaEnvrnCl(jb.get("themaEnvrnCl").toString());
                    camp.setAnimalCmgCl(jb.get("animalCmgCl").toString());
                    camp.setFirstImageUrl(jb.get("firstImageUrl").toString());

                    campApiRepository.save(camp);
                    System.out.println("A");
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
