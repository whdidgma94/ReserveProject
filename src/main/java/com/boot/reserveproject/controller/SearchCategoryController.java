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
import java.util.LinkedHashSet;
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
    public List<Camp> searchByAnimal() {
        List<Camp> searchAllList = campService.findByAnimal();
        return searchAllList;
    }
    public List<Camp> searchByClturEventAt() {
        List<Camp> searchAllList = campService.findByClturEventAt();
        return searchAllList;
    }
    public List<Camp> searchByExprnProgrmAt() {
        List<Camp> searchAllList = campService.findByExprnProgrmAt();
        return searchAllList;
    }
    public List<Camp> selectByTrlerAcmpnyAt() {
        List<Camp> searchAllList = campService.findByTrlerAcmpnyAt();
        return searchAllList;
    }
    public List<Camp> selectByCaravAcmpnyAt() {
        List<Camp> searchAllList = campService.findByCaravAcmpnyAt();
        return searchAllList;
    }
    public List<Camp> selectByInduty(String keyword) {
        List<Camp> searchAllList = campService.findByInduty(keyword);
        return searchAllList;
    }

    @GetMapping("/search/categoryCheck")
    public ResponseEntity<Object> showListByLctCl(@RequestParam(value = "categories", required = false) String[] categories) {
        List<Camp> mergedList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (String category : categories) {
            switch(category) {
                case "animalCmgCl":
                    mergedList.addAll(searchByAnimal());
                    continue;
                case "clturEventAt":
                    mergedList.addAll(searchByClturEventAt());
                    continue;
                case "exprnProgrmAt":
                    mergedList.addAll(searchByExprnProgrmAt());
                    continue;
                case "trlerAcmpnyAt":
                    mergedList.addAll(selectByTrlerAcmpnyAt());
                    continue;
                case "caravAcmpnyAt":
                    mergedList.addAll(selectByCaravAcmpnyAt());
                    continue;
                case "carav":
                    mergedList.addAll(selectByInduty("카라반"));
                    continue;
                case "glamp":
                    mergedList.addAll(selectByInduty("글램핑"));
                    continue;
                case "car":
                    mergedList.addAll(selectByInduty("자동차야영"));
                    continue;
                default:
            }
        }


        mergedList = new ArrayList<>(new LinkedHashSet<>(mergedList)); // 중복 제거
        int count = mergedList.size();
        System.out.println("listCount : "+count);
        try {
            String jsonString = mapper.writeValueAsString(mergedList);
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
