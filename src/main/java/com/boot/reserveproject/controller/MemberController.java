package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.form.LoginForm;
import com.boot.reserveproject.form.MemberForm;
import com.boot.reserveproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/pc/member/new")
    public String joinFormPc(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        System.out.println();
        return "pc/member/memberJoinForm";
    }
    @GetMapping("/mobile/member/new")
    public String joinFormMobile(Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "mobile/member/memberJoinForm";
    }

    @PostMapping("/pc/member/new")
    public String joinMemberPc(@Valid MemberForm form, BindingResult result) {
//        if(result.hasErrors()){
//            return "redirect:/member/new";
//        }
        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPw(form.getPw());
        System.out.println("domain = " + form.getEmailDomain());
        member.setEmail(form.getEmailId()+"@"+form.getEmailDomain());
        member.setPostcode(form.getPostcode());
        member.setRoadAddress(form.getRoadAddress());
        member.setJibunAddress(form.getJibunAddress());
        member.setDetailAddress(form.getDetailAddress());
        member.setPhone(form.getPhone());
        System.out.println("form.getPhone() = " + form.getPhone());
        member.setRegNum1(form.getRegNum1());
        member.setRegNum2(form.getRegNum2());
        if(form.getRegNum2().substring(0,1).equals("1")||form.getRegNum2().substring(0,1).equals("3")){
            member.setGender("male");
        }else{
            member.setGender("female");
        }
        memberService.join(member);
        return "pc/index";
    }
    @PostMapping("/mobile/member/new")
    public String joinMemberMobile(@Valid MemberForm form, BindingResult result) {
//        if(result.hasErrors()){
//            return "redirect:/member/new";
//        }
        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPw(form.getPw());
        System.out.println("domain = " + form.getEmailDomain());
        member.setEmail(form.getEmailId()+"@"+form.getEmailDomain());
        member.setPostcode(form.getPostcode());
        member.setRoadAddress(form.getRoadAddress());
        member.setJibunAddress(form.getJibunAddress());
        member.setDetailAddress(form.getDetailAddress());
        member.setPhone(form.getPhone());
        System.out.println("form.getPhone() = " + form.getPhone());
        member.setRegNum1(form.getRegNum1());
        member.setRegNum2(form.getRegNum2());
        if(form.getRegNum2().substring(0,1).equals("1")||form.getRegNum2().substring(0,1).equals("3")){
            member.setGender("male");
        }else{
            member.setGender("female");
        }
        memberService.join(member);
        return "mobile/index";
    }
    @GetMapping("/pc/member/login")
    public String loginFormPc(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "pc/member/memberLoginForm";
    }
    @GetMapping("/mobile/member/login")
    public String loginFormMobile(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "mobile/member/memberLoginForm";
    }
    @PostMapping("/pc/member/login")
    private String loginMemberPc(@Valid LoginForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "redirect:/pc/member/login";
        }
        if (memberService.checkLogin(form.getLoginId(), form.getPw())) {
            if(form.getLoginId().equals("admin")){
                return "admin/index";
            }
            session.setAttribute("log", form.getLoginId());
        }
        return "redirect:/pc/main";
    }
    @PostMapping("/mobile/member/login")
    private String loginMemberMobile(@Valid LoginForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "redirect:/mobile/member/login";
        }
        if (memberService.checkLogin(form.getLoginId(), form.getPw())) {
            session.setAttribute("log", form.getLoginId());
            System.out.println("form.getLoginId() = " + form.getLoginId());
            System.out.println("form.getPw() = " + form.getPw());
        }
        return "redirect:/mobile/index";
    }
    @GetMapping("/pc/member/logout")
    private String logoutPc(HttpSession session){
        session.removeAttribute("log");
        return "redirect:/pc/main";
    }
    @GetMapping("/mobile/member/logout")
    private String logoutMobile(HttpSession session){
        session.removeAttribute("log");
        return "redirect:/mobile/index";
    }

    @ResponseBody
    @PostMapping("/validId")
    public String validId(@RequestParam("loginId") String loginId){
        if(memberService.validId(loginId)){
            return "true";
        }
        return "false";
    }
    @GetMapping("/pc/member/myPage")
    public String myPagePc(HttpSession session, Model model){
        model.addAttribute("member", memberService.selectMemberById((String) session.getAttribute("log")));
        return "pc/member/memberMyPage";
    }
    @GetMapping("/mobile/member/myPage")
    public String myPageMobile(HttpSession session, Model model){
        model.addAttribute("member", memberService.selectMemberById((String) session.getAttribute("log")));
        return "mobile/member/memberMyPage";
    }

    @GetMapping("/pc/member/find")
    public String findId(@RequestParam("type") String type, Model model) {
        model.addAttribute("type", type);
        return "pc/member/memberFindAccount";
    }


}