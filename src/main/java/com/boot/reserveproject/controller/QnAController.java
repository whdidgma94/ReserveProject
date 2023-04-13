package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Notice;
import com.boot.reserveproject.domain.QnA;
import com.boot.reserveproject.form.QnAForm;
import com.boot.reserveproject.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;

    @GetMapping("/receiveQnAList")
    public String getReceiveQnAList(String receiver, Model model, @RequestParam("type") String type){
        model.addAttribute(qnAService.getReceiveQnA(receiver));
        if (type.equals("pc")) {
            return "pc/QnA/QnAList";
        } else if (type.equals("mobile")) {
            return "mobile/QnA/QnAList";
        } else {
            return "admin/QnA/QnAList";
        }
    }
    @GetMapping("/sendQnAList")
    public String getSendQnAList(HttpSession session, Model model, @RequestParam("type") String type){
        String sender = (String) session.getAttribute("log");
        model.addAttribute(qnAService.getSendQnA(sender));
        if (type.equals("pc")) {
            return "pc/QnA/QnAList";
        } else if (type.equals("mobile")) {
            return "mobile/QnA/QnAList";
        } else {
            return "admin/QnA/QnAList";
        }
    }
    @GetMapping("/qnAInfo")
    public ResponseEntity<QnA> getNoticeInfo(@RequestParam("id") Long id) {
        QnA qna = qnAService.getOneQnA(id);
        return ResponseEntity.ok(qna);
    }

@GetMapping("/sendQnA")
public String qnAForm(Model model, @RequestParam("type") String type){
        model.addAttribute("qnAForm", new QnAForm());
    if (type.equals("pc")) {
        return "pc/QnA/sendQnA";
    } else if (type.equals("mobile")) {
        return "mobile/QnA/sendQnA";
    } else {
        return "admin/QnA/sendQnA";
    }
}
    @PostMapping("/sendQnA")
    public String sendQnA(@Valid QnAForm form, BindingResult result, @RequestParam("type") String type, HttpSession session){
        QnA qna = new QnA();
        qna.setCategory(form.getCategory());
        qna.setContext(form.getContext());
        qna.setSender((String)session.getAttribute("log"));
        qna.setReceiver("admin");
        qna.setStatus("NotRead");
        qnAService.createQnA(qna);
        if (type.equals("pc")) {
            return "redirect:/pc/index";
        } else if (type.equals("mobile")) {
            return "redirect:/mobile/index";
        } else {
            return "redirect:/admin/index";
        }
    }
}
