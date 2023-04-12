package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.service.CampService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchCategoryController {
    @Autowired
    private CampService campService;

    @GetMapping("/search/category")
    public String showCategoryList(Model model) {
        campString(model);
        return "pc/search/searchCategory";
    }

    public List<Camp> searchByAnimal(int page) {
        int min = page * 10 - 10;
        int max = page * 10 - 1;
        List<Camp> searchAllList = campService.findByAnimal();
        List<Camp> campList = new ArrayList<>();
        for (int i = min; i <= max && i < searchAllList.size(); i++) {
            campList.add(searchAllList.get(i));
        }
        return campList;
    }

    @GetMapping("/search/categoryCheck")
    public ResponseEntity<Object> showListByLctCl(@RequestParam(value = "categories", required = false) String[] categories) {
        List<Camp> campList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals("animalCmgCl")) {
                campList = searchByAnimal(1);
                for (int j = 0; j < 3; j++) {
                    System.out.println("campList = " + campList.get(j).getAnimalCmgCl());
                }
            }
        }
        System.out.println("campList = " + campList.size());
        try {
            String jsonString = mapper.writeValueAsString(campList);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void campString(Model model) {
        List<Camp> campList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Camp camp = campService.selectOneById((long) i);
            System.out.println("i = " + i);
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

            String[] thema = {"즐거운캠핑"};
            if (!campList.get(i).getThemaEnvrnCl().equals("")) {
                thema = campList.get(i).getThemaEnvrnCl().split(",");
            }
            themaList.add(Arrays.asList(thema));

            System.out.println(Arrays.toString(thema));
            System.out.println(Arrays.toString(sbrsCl));
        }
        model.addAttribute("sbrsClList", sbrsClList);
        model.addAttribute("themaList", themaList);
    }

}
