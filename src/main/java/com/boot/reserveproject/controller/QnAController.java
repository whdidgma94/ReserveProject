package com.boot.reserveproject.controller;

import com.boot.reserveproject.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;

    @GetMapping("/myQnAList")
    public String getMyQnAList(String receiver, Model model, @RequestParam("type") String type){
        model.addAttribute(qnAService.getMyQnA(receiver));
        if (type.equals("pc")) {
            return "pc/QnA/QnAList";
        } else if (type.equals("mobile")) {
            return "mobile/QnA/QnAList";
        } else {
            return "admin/QnA/QnAList";
        }
    }
}
