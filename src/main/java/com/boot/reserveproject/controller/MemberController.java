package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.domain.MemberLikes;
import com.boot.reserveproject.form.LoginForm;
import com.boot.reserveproject.form.MemberForm;
import com.boot.reserveproject.form.MemberUpdateForm;
import com.boot.reserveproject.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final CampService campService;
    private final QnAService qnAService;
    private final BoardService boardService;
    private final CommentsService commentsService;

    @GetMapping("/pc/member/new")
    public String joinFormPc(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "pc/member/memberJoinForm";
    }

    @GetMapping("/pc/member/update")
    public String updateFormPc(Model model, HttpSession session) {
        String loginId = (String) session.getAttribute("log");
        Member member = memberService.selectMemberById(loginId);
        String email = member.getEmail();
        int index = email.indexOf("@");
        String emailId = email.substring(0, index);
        String emailDomain = email.substring(index + 1);
        model.addAttribute("phone", member.getPhone().replace("-", ""));
        model.addAttribute("emailId", emailId);
        model.addAttribute("emailDomain", emailDomain);
        model.addAttribute("memberUpdateForm", new MemberUpdateForm());
        model.addAttribute("member", memberService.selectMemberById(loginId));
        return "pc/member/memberUpdateForm";
    }

    @GetMapping("/mobile/member/update")
    public String updateFormMobile(Model model, HttpSession session) {
        String loginId = (String) session.getAttribute("log");
        String email = memberService.selectMemberById(loginId).getEmail();
        int index = email.indexOf("@");
        String emailId = email.substring(0, index);
        String emailDomain = email.substring(index + 1);
        model.addAttribute("emailId", emailId);
        model.addAttribute("emailDomain", emailDomain);
        model.addAttribute("memberUpdateForm", new MemberUpdateForm());
        model.addAttribute("member", memberService.selectMemberById(loginId));
        return "mobile/member/memberUpdateForm";
    }

    @GetMapping("/mobile/member/new")
    public String joinFormMobile(Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "mobile/member/memberJoinForm";
    }

    @PostMapping("/pc/member/new")
    public String joinMemberPc(@Valid MemberForm form, BindingResult result) {

        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPw(form.getPw());
        member.setEmail(form.getEmailId() + "@" + form.getEmailDomain());
        member.setPostcode(form.getPostcode());
        member.setRoadAddress(form.getRoadAddress());
        member.setJibunAddress(form.getJibunAddress());
        member.setDetailAddress(form.getDetailAddress());
        member.setPhone(form.getPhone());
        member.setRegNum1(form.getRegNum1());
        member.setRegNum2(form.getRegNum2());
        if (form.getRegNum2().substring(0, 1).equals("1") || form.getRegNum2().substring(0, 1).equals("3")) {
            member.setGender("male");
        } else {
            member.setGender("female");
        }
        memberService.join(member);
        return "redirect:/pc/main";
    }

    @PostMapping("/pc/member/update")
    public String updateMemberPc(@Valid MemberUpdateForm form, HttpSession session, BindingResult result) {
        String loginId = (String) session.getAttribute("log");
        System.out.println("loginId = " + loginId);
        Member member = memberService.selectMemberById(loginId);
        member.setPw(form.getPw());
        System.out.println("pw = " + form.getPw());
        member.setEmail(form.getEmailId() + "@" + form.getEmailDomain());
        member.setPostcode(form.getPostcode());
        member.setRoadAddress(form.getRoadAddress());
        member.setJibunAddress(form.getJibunAddress());
        member.setDetailAddress(form.getDetailAddress());
        member.setPhone(form.getPhone());
        System.out.println("phone = " + form.getPhone());
        memberService.updateMember(member);
        return "redirect:/pc/main";
    }

    @PostMapping("/mobile/member/update")
    public String updateMemberMobile(@Valid MemberUpdateForm form, HttpSession session, BindingResult result) {
        String loginId = (String) session.getAttribute("log");
        Member member = memberService.selectMemberById(loginId);
        member.setPw(form.getPw());
        member.setEmail(form.getEmailId() + "@" + form.getEmailDomain());
        member.setPostcode(form.getPostcode());
        member.setRoadAddress(form.getRoadAddress());
        member.setJibunAddress(form.getJibunAddress());
        member.setDetailAddress(form.getDetailAddress());
        member.setPhone(form.getPhone());
        memberService.updateMember(member);
        return "redirect:/mobile/main";
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
        member.setEmail(form.getEmailId() + "@" + form.getEmailDomain());
        member.setPostcode(form.getPostcode());
        member.setRoadAddress(form.getRoadAddress());
        member.setJibunAddress(form.getJibunAddress());
        member.setDetailAddress(form.getDetailAddress());
        member.setPhone(form.getPhone());
        System.out.println("form.getPhone() = " + form.getPhone());
        member.setRegNum1(form.getRegNum1());
        member.setRegNum2(form.getRegNum2());
        if (form.getRegNum2().substring(0, 1).equals("1") || form.getRegNum2().substring(0, 1).equals("3")) {
            member.setGender("male");
        } else {
            member.setGender("female");
        }
        memberService.join(member);
        return "redirect:/mobile/main";
    }

    @GetMapping("/pc/member/login")
    public String loginFormPc() {
        return "pc/member/memberLoginForm";
    }

    @GetMapping("/mobile/member/login")
    public String loginFormMobile() {
        return "mobile/member/memberLoginForm";
    }

    @GetMapping("/pc/login")
    @ResponseBody
    private String loginMemberPc(@RequestParam("loginId") String loginId, @RequestParam("pw") String pw, HttpSession session) {
        if (memberService.checkLogin(loginId, pw)) {
            session.setAttribute("log", loginId);
            if (loginId.equals("admin")) {
                return "admin";
            }
            return "true";
        } else {
            System.out.println("4");
            return "false";
        }
    }

    @GetMapping("/mobile/login")
    @ResponseBody
    private String loginMemberMobile(@RequestParam("loginId") String loginId, @RequestParam("pw") String pw, HttpSession session) {
        if (memberService.checkLogin(loginId, pw)) {
            session.setAttribute("log", loginId);
            return "true";
        } else {
            System.out.println("4");
            return "false";
        }
    }

    @GetMapping("/pc/member/logout")
    public String logout(HttpServletRequest request, HttpSession session) throws ServletException {
        session.removeAttribute("log");
//        request.logout();

        return "redirect:/pc/main";
    }


    @GetMapping("/mobile/member/logout")
    private String logoutMobile(HttpSession session) {
        session.removeAttribute("log");
        return "redirect:/mobile/main";
    }

    @ResponseBody
    @GetMapping("/validId")
    public String validId(@RequestParam("loginId") String loginId) {
        if (memberService.validId(loginId)) {
            return "true";
        }
        return "false";
    }

    @GetMapping("/pc/member/myPage")
    public String myPagePc(HttpSession session, Model model) {
        model.addAttribute("member", memberService.selectMemberById((String) session.getAttribute("log")));
        return "pc/member/memberMyPage";
    }

    @GetMapping("/mobile/member/myPage")
    public String myPageMobile(HttpSession session, Model model) {
        model.addAttribute("member", memberService.selectMemberById((String) session.getAttribute("log")));
        return "mobile/member/memberMyPage";
    }

    @ResponseBody
    @PostMapping("/member/delete")
    public String deleteMember(@RequestParam("pw") String pw, HttpSession session) {
        String loginId = (String) session.getAttribute("log");
        System.out.println(memberService.checkLogin(loginId, pw));
        if (memberService.checkLogin(loginId, pw)) {
            commentsService.deleteCommentsByLoginId(loginId);
            boardService.deleteBoardByLoginId(loginId);

            memberService.deleteMemberByLoginId(loginId);
            campService.deleteMemberLikesByLoginId(loginId);
            qnAService.deleteBySender(loginId);
            session.removeAttribute("log");
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/pc/member/find")
    public String findIdPc(@RequestParam("type") String type, Model model) {
        model.addAttribute("type", type);
        return "pc/member/memberFindAccount";
    }

    @GetMapping("/mobile/member/find")
    public String findIdMobile(@RequestParam("type") String type, Model model) {
        model.addAttribute("type", type);
        return "mobile/member/memberFindAccount";
    }

    @PostMapping("/addLikes")
    @ResponseBody
    public String addLikes(@RequestParam("contentId") Long contentId, HttpSession session) {
        Camp camp = campService.getCampById(contentId);
        String loginId = (String) session.getAttribute("log");
        Member member = memberService.selectMemberById(loginId);
        memberService.addMemberLikes(camp, member);

        return "true";
    }

    @PostMapping("/deleteLikes")
    @ResponseBody
    public String deleteLikes(@RequestParam("contentId") Long contentId, HttpSession session) {
        Camp camp = campService.getCampById(contentId);
        String loginId = (String) session.getAttribute("log");
        Member member = memberService.selectMemberById(loginId);
        memberService.deleteMemberLikes(camp, member);
        return "true";
    }

}