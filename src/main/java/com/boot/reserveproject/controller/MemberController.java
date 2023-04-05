package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    //private final MemberService service = new MemberService();
    private final MemberService service;

    @Autowired
    MemberController(MemberService memberService) {
        this.service = memberService;
    }

    @GetMapping
    public String createForm() {
        return "members/joinForm";
    }

    @PostMapping
    public String create(@ModelAttribute MemberForm form, Model model) {
        Member member =new Member();

        try {
            service.join(member);
        } catch (IllegalStateException e) {
            model.addAttribute("msg", e.getMessage());
            return "members/joinForm";
        }
        return "redirect:/member/members";
    }

    @GetMapping("/members")  // member/members
    public String getMemberList(Model model) {
        List<Member> list = null;
        try {
            list = service.findAllMembers();
        } catch (IllegalStateException e) {
            return "members/joinForm";
        }

        model.addAttribute("list", list);

        return "members/list";
    }

    @GetMapping("{keyId}")
    public String deleteMember(@PathVariable Long keyId) {
        service.removeMember(keyId);
        return "redirect:/member/members";
    }

    @ResponseBody
    @DeleteMapping("{keyId}")
    public String deleteMemberAjax(@PathVariable Long keyId) {
        service.removeMember(keyId);
        return "ok";
    }
}
