package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CampController {
    @Autowired
    private CampService campService;

    @GetMapping("main")
    public String getCamp(Model model) {
        Camp camp = campService.getCampById(4L);
        model.addAttribute("camp", camp);
        return "pc/index";
    }

    @GetMapping("detailCamp")
    public String getDetailsCamp(@RequestParam Long contentId, Model model) {
        Camp camp = campService.getCampById(contentId);
        model.addAttribute("camp", camp);
        return "pc/camp/campDetail";
    }


}
