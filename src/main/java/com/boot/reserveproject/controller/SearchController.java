package com.boot.reserveproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SearchController {
    @GetMapping("/search/mapIntro")
    public String showMapIntro(){
        return "pc/search/searchMap";
    }
}
