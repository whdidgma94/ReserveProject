package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Notice;
import com.boot.reserveproject.form.NoticeForm;
import com.boot.reserveproject.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

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
        noticeService.createNotice(notice);
        return "admin/notice/noticeList";
    }
    
    @GetMapping("/noticeList")
    public String noticeList(Model model, @RequestParam("type") String type){
        List<Notice> list = noticeService.getAllNotice();
        model.addAttribute("noticeList", list);
        if(type==null){
            return null;
        }
        return "admin/notice/noticeList";
    }

    @PostMapping("/admin/deleteNotice")
    public String deleteNotice(@RequestParam("id") Long id){
        noticeService.deleteNotice(id);
        return "/admin/notice/noticeList";
    }

    @GetMapping("/noticeInfo")
    public ResponseEntity<Notice> getNoticeInfo(@RequestParam("id") Long id) {
        Notice notice = noticeService.getOneNotice(id);
        return ResponseEntity.ok(notice);
    }
}
