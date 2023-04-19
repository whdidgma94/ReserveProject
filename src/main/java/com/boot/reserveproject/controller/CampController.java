package com.boot.reserveproject.controller;

import com.boot.reserveproject.api.CampApiController;
import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.service.CampService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CampController {
    @Autowired
    private CampService campService;
    @Autowired
    private CampApiController campApiController;

    @GetMapping("/pc/main")
    public String getCampPc(Model model) {
        campString(model);
        return "pc/index";
    }

    @GetMapping("/mobile/main")
    public String getCampMobile(Model model) {
        campString(model);
        return "mobile/index";
    }

    @GetMapping("/camp/campList")
    public String getCampList(Model model) {
        campString(model);
        return "pc/camp/campList";
    }

    public List<Camp> searchByLctCls(String lctCl) {
        List<Camp> temp = campService.findBylctCl(lctCl);
        List<Camp> searchAllList = new ArrayList<>();
        for (int i = 0; i < 18 && i < temp.size(); i++) {
            searchAllList.add(i, temp.get(i));
        }
        return searchAllList;
    }

    public List<Camp> searchByThema(String thema) {
        List<Camp> temp = campService.findByThema(thema);
        List<Camp> searchAllList = new ArrayList<>();
        for (int i = 0; i < 18 && i < temp.size(); i++) {
            searchAllList.add(i, temp.get(i));
        }
        return searchAllList;
    }

    private void campString(Model model) {
        String[] iconText = {"오토캠핑", "카라반", "글램핑", "반려동물", "호수", "숲", "일출", "일몰"};
        String[] iconTextId = {"car", "carav", "glamp", "animalCmgCl", "lct07", "lct04", "thema01", "thema02"};
        model.addAttribute("iconText", iconText);
        model.addAttribute("iconTextId", iconTextId);
        model.addAttribute("lctCls", searchByLctCls("해변"));
        model.addAttribute("themas", searchByThema("낚시"));

    }

    @GetMapping("/pc/detailCamp")
    public String getDetailsCampPc(@RequestParam Long contentId, Model model) {
        Camp camp = campService.getCampById(contentId);
        model.addAttribute("camp", camp);

        //campApiController에 있는 이미지 api
        String[] campImageList = campApiController.getImageList(contentId);
        model.addAttribute("campImageList", campImageList);
        return "pc/camp/campDetail";
    }

    @GetMapping("/mobile/detailCamp")
    public String getDetailsCampMobile(@RequestParam Long contentId, Model model) {
        Camp camp = campService.getCampById(contentId);
        model.addAttribute("camp", camp);

        //campApiController에 있는 이미지 api
        String[] campImageList = campApiController.getImageList(contentId);
        model.addAttribute("campImageList", campImageList);
        return "mobile/camp/campDetail";
    }

    public List<Camp> searchByLctCl(String lctCl) {
        List<Camp> campList = campService.findBylctCl(lctCl);
        return campList;
    }

    @GetMapping("/camp/cltList")
    public ResponseEntity<Object> showListByLctCl(@RequestParam(value = "lctCl", required = false) String lctCl) {
        List<Camp> campList = searchByLctCls(lctCl);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(campList);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/camp/themaList")
    public ResponseEntity<Object> showListByThema(@RequestParam(value = "lctCl", required = false) String lctCl) {
        List<Camp> campList = searchByThema(lctCl);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(campList);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/memberLikesList")
    public String memberLikesList(HttpSession session, @Param("type") String type, Model model) {
        List<Camp> memberLikesList = campService.selectMemberListByLoginId((String) session.getAttribute("log"));
        model.addAttribute("memberLikesList", memberLikesList);
        if (type.equals("pc")) {
            return "pc/member/memberLikes";
        } else {
            return "mobile/member/memberLikes";
        }
    }
}
