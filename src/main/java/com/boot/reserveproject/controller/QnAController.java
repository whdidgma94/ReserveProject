package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.QnA;
import com.boot.reserveproject.form.QnAForm;
import com.boot.reserveproject.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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

    @PostMapping("/sendQnA")
    public String createForm(@Valid QnAForm form, BindingResult result){
        QnA qna = new QnA();
        return null;
    }
}
