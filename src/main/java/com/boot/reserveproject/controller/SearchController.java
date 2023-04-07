package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.repository.CampRepository;
import com.boot.reserveproject.service.CampService;
import com.boot.reserveproject.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SearchController {
    private final CampService campService;
    @GetMapping("/search/mapIntro")
    public String showMapIntro() {
        return "pc/search/searchMap";
    }

    @GetMapping("/search/map")
    public ResponseEntity<Object> showMap(@RequestParam(value="keyword", required=false) String keyword, @RequestParam("type") String type) {
        if (keyword == null) {
            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/pc/search/searchMap").build();
        }
        // keyword가 존재하는 경우 이하 코드 실행
        List<Camp> campList = new ArrayList<>();
        try {
            if (type.equals("name")) {
                campList = searchByName(keyword);
            } else if (type.equals("theme")) {
                campList = searchByTheme(keyword);
            } else if (type.equals("location")) {
                campList = searchByAddress(keyword);
            }
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(campList);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public List<Camp> searchByName(String keyword) {
        List<Camp> campList= campService.getCampListByName(keyword);
        System.out.println("해당 검색어를 포함한 캠핑장의 이름의 개수는: "+campList.size());
        return campList;
    }

    public List<Camp> searchByTheme(String keyword) {
        List<Camp> campList=campService.getCampListByTheme(keyword);
        System.out.println("테마들어옴");
        return campList;
    }

    public List<Camp> searchByAddress(String keyword) {
        List<Camp>campList=campService.getCampListByAddress(keyword);
        System.out.println("위치 들어옴");
        return campList;

    }
}
