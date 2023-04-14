package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @GetMapping("/admin/memberList")
    public String getMembers(Model model) {
        model.addAttribute("memberList", adminService.allMembers());
        return "/admin/member/memberList";
    }

    @PostMapping("/admin/deleteMember")
    public String deleteMember(@RequestParam("id") Long id) {

        adminService.removeMember(id);
        return "/admin/member/memberList";
    }

    @GetMapping("/admin/memberInfo")
    public ResponseEntity<Member> getMemberInfo(@RequestParam("id") Long id) {
        Member member = adminService.oneMember(id);
        return ResponseEntity.ok(member);
    }

}
