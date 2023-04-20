package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.repository.CampApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;

@Service
public class CampApiService {

    @Autowired
    private CampApiRepository campApiRepository;
    public void saveCamps(List<HashMap<String, Object>> besedList) {
        RestTemplate restTemplate = new RestTemplate();

        for (HashMap<String, Object> camps : besedList) {
            Camp camp = new Camp();
            camp.setContentId(Long.parseLong((String)camps.get("contentId")));
            camp.setFacltNm((String)camps.get("facltNm"));
            camp.setLineIntro((String)camps.get("lineIntro"));
            camp.setLineIntro((String)camps.get("lineIntro"));
            camp.setIntro((String)camps.get("intro"));

            camp.setFacltDivNm((String)camps.get("facltDivNm"));
            camp.setManageSttus((String)camps.get("manageSttus"));
            camp.setHvofBgnde((String)camps.get("hvofBgnde"));
            camp.setHvofEnddle((String)camps.get("hvofEnddle"));
            camp.setHvofEnddle((String)camps.get("hvofEnddle"));
            camp.setInduty((String)camps.get("induty"));
            camp.setLctCl((String)camps.get("lctCl"));

            camp.setDoNm((String)camps.get("doNm"));
            camp.setSigunguNm((String)camps.get("sigunguNm"));
            camp.setZipcode((String)camps.get("zipcode"));
            camp.setAddr1((String)camps.get("addr1"));
            camp.setAddr2((String)camps.get("addr2"));

            camp.setMapX(Double.parseDouble((String)camps.get("mapX")));
            camp.setMapY(Double.parseDouble((String)camps.get("mapY")));

            camp.setDirection((String)camps.get("direction"));
            camp.setTel((String)camps.get("tel"));
            camp.setHomepage((String)camps.get("homepage"));

            camp.setSiteCount((String)camps.get("siteCount"));
            camp.setSitedStnc(Long.parseLong((String)camps.get("sitedStnc")));
            camp.setSite((String)camps.get("site"));

            camp.setSiteBottom((String)camps.get("siteBottom"));

            camp.setTooltip((String)camps.get("tooltip"));

            camp.setGlampInnerFclty((String)camps.get("glampInnerFclty"));
            camp.setCaravInnerFclty((String)camps.get("caravInnerFclty"));

            camp.setOperPdCl((String)camps.get("operPdCl"));
            camp.setOperDeCl((String)camps.get("operDeCl"));
            camp.setTrlerAcmpnyAt((String)camps.get("trlerAcmpnyAt"));
            camp.setCaravAcmpnyAt((String)camps.get("caravAcmpnyAt"));

            camp.setSanitary((String)camps.get("sanitary"));

            camp.setBrazierCl((String)camps.get("brazierCl"));
            camp.setSbrsCl((String)camps.get("sbrsCl"));
            camp.setSbrsEtc((String)camps.get("sbrsEtc"));

            camp.setFpSystem((String)camps.get("fpSystem"));

            camp.setPosblFcltyCl((String)camps.get("posblFcltyCl"));
            camp.setPosblFcltyEtc((String)camps.get("posblFcltyEtc"));
            camp.setClturEventAt((String)camps.get("clturEventAt"));
            camp.setClturEvent((String)camps.get("clturEvent"));
            camp.setExprnProgrmAt((String)camps.get("exprnProgrmAt"));
            camp.setExprnProgrm((String)camps.get("exprnProgrm"));

            camp.setThemaEnvrnCl((String)camps.get("themaEnvrnCl"));
            camp.setAnimalCmgCl((String)camps.get("animalCmgCl"));
            camp.setFirstImageUrl((String)camps.get("firstImageUrl"));

            campApiRepository.save(camp);
        }
    }

    public void updateApi(){

    }
}
