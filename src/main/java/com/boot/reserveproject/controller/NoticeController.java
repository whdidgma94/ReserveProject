package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Notice;
import com.boot.reserveproject.form.MemberForm;
import com.boot.reserveproject.form.NoticeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    @GetMapping("/admin/notice/new")
    public String noticeForm(Model model) {
        model.addAttribute("noticeForm", new NoticeForm());
        return "admin/notice/addNotice";
    }
    @PostMapping("/admin/notice/new")
    public String addNotice(@Valid NoticeForm form, BindingResult result){
        Notice notice = new Notice();
        notice.setSubject(form.getSubject());
        notice.setContext(form.getSubject());
        return "admin/index";
    }
}
