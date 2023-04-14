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

    @GetMapping("/sendQnAList")
    public String getSendQnAList(HttpSession session, Model model, @RequestParam("type") String type) {
        if (type.equals("admin")) {
            model.addAttribute("qnAList", qnAService.getQuestionList());
        } else {
            String log = (String) session.getAttribute("log");
            model.addAttribute("qnAList", qnAService.getSendQnA(log));
        }
        if (type.equals("pc")) {
            return "pc/QnA/QnAList";
        } else if (type.equals("mobile")) {
            return "mobile/QnA/QnAList";
        } else {
            return "admin/QnA/QnAList";
        }
    }

    @GetMapping("/qnAInfo")
    public ResponseEntity<QnA> getNoticeInfo(@RequestParam("id") Long id, HttpSession session) {
        String log = (String) session.getAttribute("log");
        QnA qna = qnAService.getOneQnA(id);
        if (!qna.getSender().equals(log)) {
            qnAService.updateStatusRead(id);
        }
        return ResponseEntity.ok(qna);
    }

    @GetMapping("/sendQnA")
    public String qnAForm(Model model, @RequestParam(value = "type") String type, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("qnAForm", new QnAForm());
        if (type.equals("pc")) {
            return "pc/QnA/sendQnA";
        } else if (type.equals("mobile")) {
            return "mobile/QnA/sendQnA";
        } else {
            model.addAttribute("question", qnAService.getOneQnA(id));
            return "admin/QnA/sendQnA";
        }
    }

    @PostMapping("/read")
    public void updateStatusRead(@RequestParam("id") Long id) {

    }

    @PostMapping("/sendQnA")
    public String sendQnA(@Valid QnAForm form, BindingResult result, @RequestParam("type") String type, @RequestParam(value = "id", required = false) Long id, HttpSession session) {
        QnA qna = new QnA();
        if (type.equals("admin")) {
            QnA question = qnAService.getOneQnA(id);
            qna.setConnectedId(id);
            qna.setSender("admin");
            qna.setReceiver(question.getSender());
            qnAService.updateStatusDone(id);
            qna.setCategory(question.getCategory());
        } else {
            qna.setSender((String) session.getAttribute("log"));
            qna.setReceiver("admin");
        qna.setStatus("NotRead");
        }
        qna.setCategory(form.getCategory());
        qna.setContext(form.getContext());
        qnAService.createQnA(qna);
        if (type.equals("pc")) {
            return "redirect:/pc/index";
        } else if (type.equals("mobile")) {
            return "redirect:/mobile/index";
        } else {
            return "redirect:/admin/index";
        }
    }

    @GetMapping("/qnAAnswer")
    public ResponseEntity<QnA> getAnswer(@RequestParam("id") Long id) {
        QnA qna = qnAService.getAnswer(id);
        return ResponseEntity.ok(qna);
    }
}
