package com.boot.reserveproject.controller;

import com.boot.reserveproject.api.CampApiController;
import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CampController {
    @Autowired
    private CampService campService;
    @Autowired
    private CampApiController campApiController;

    @GetMapping("/main")
    public String getCamp(Model model) {
        campString(model);
        return "pc/index";
    }

    @GetMapping("/camp/campList")
    public String getCampList(Model model) {
        campString(model);
        return "pc/camp/campList";
    }

    private void campString(Model model) {
        List<Camp> campList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Camp camp = campService.selectOneById((long) i);
            if (camp != null) {
                campList.add(camp);
            }
        }
        model.addAttribute("campList", campList);

        List<List<String>> sbrsClList = new ArrayList<>();
        List<List<String>> themaList = new ArrayList<>();
        for (int i = 0; i < campList.size(); i++) {
            String tempSbrsCl = campList.get(i).getSbrsCl();
            String[] sbrsCl = tempSbrsCl.split(",");
            sbrsClList.add(Arrays.asList(sbrsCl));

            String[] thema = {"없음"};
            if (!campList.get(i).getThemaEnvrnCl().equals("")) {
                thema = campList.get(i).getThemaEnvrnCl().split(",");
            }
            themaList.add(Arrays.asList(thema));
            System.out.println(Arrays.toString(thema));
        }
        model.addAttribute("sbrsClList", sbrsClList);
        model.addAttribute("themaList", themaList);
    }

    @GetMapping("detailCamp")
    public String getDetailsCamp(@RequestParam Long contentId, Model model) {
        Camp camp = campService.getCampById(contentId);
        model.addAttribute("camp", camp);

        //campApiController에 있는 이미지 api
        String[] campImageList = campApiController.getImageList(contentId);
        model.addAttribute("campImageList", campImageList);
        return "pc/camp/campDetail";
    }

}
