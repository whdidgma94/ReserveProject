package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "pc/member/memberJoinForm";
    }

    @PostMapping("/member/new")
    public String create(@Valid MemberForm form, BindingResult result) {
//        if(result.hasErrors()){
//            log.info("error = {}" , result.getAllErrors());
//            return "redirect:/member/new";
//        }

        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPw(form.getPw());
        System.out.println("123" + form.getEmailDomain());
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

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        return "pc/member/memberJoinForm";
    }

    @ResponseBody
    @PostMapping("/validId")
    public String validId(@RequestParam("loginId") String loginId){
        if(memberService.validId(loginId)){
            return "true";
        }
        return "false";
    }

}