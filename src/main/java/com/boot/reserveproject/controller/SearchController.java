package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.repository.CampRepository;
import com.boot.reserveproject.service.CampService;
import com.boot.reserveproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

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
    public String showMap(@RequestParam("keyword") String keyword, @RequestParam("type") String type, Model model) {
        if (type.equals("name")) {
            searchByName(keyword);
        } else if (type.equals("theme")) {
            searchByTheme(keyword);
        } else if (type.equals("location")) {
            searchByLocation(keyword);
        }
        System.out.println(type);
        System.out.println(keyword);
        model.addAttribute("test", "test");
        return "pc/search/searchMap";
    }

    public void searchByName(String keyword) {
        List<Camp> campList= campService.getCampListByName(keyword);
        System.out.println("해당 검색어를 포함한 캠핑장의 이름의 개수는: "+campList.size());

    }

    public void searchByTheme(String keyword) {
        System.out.println("테마들어옴");
            
    }

    public void searchByLocation(String keyword) {
        System.out.println("위치 들어옴");

    }
}
