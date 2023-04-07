package com.boot.reserveproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequiredArgsConstructor
public class SearchController {
    @GetMapping("/search/mapIntro")
    public String showMapIntro(){
        return "pc/search/searchMap";
    }
    @GetMapping("/search/map")
    public String showMap(@RequestParam("q")String q,Model model){



         final double[][] rdList = {
                {37.5665, 126.9780},
                {37.4559, 126.7052},
                {37.5651, 126.8495},
                {37.4772, 126.6249},
                {35.1595, 129.0256},
                {35.1796, 128.9966},
                {35.8700, 128.5914},
                {35.2332, 129.0823},
                {36.3504, 127.3845},
                {36.6384, 127.6966},
                {37.2733, 127.0089},
                {37.8996, 127.1108},
                {35.1726, 126.9107},
                {35.5384, 129.3114},
                {35.1802, 126.8845},
                {36.3324, 127.3948},
                {36.5945, 126.9455},
                {36.6373, 126.8312},
                {37.6448, 127.0276},
                {37.3518, 127.9458}
        };

        model.addAttribute("list",rdList);





        return "pc/search/searchMap";
    }
}
