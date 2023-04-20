package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.EmailMessage;
import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.service.AuthService;
import com.boot.reserveproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;
    private final AuthService authService;

    @GetMapping("/form")
    public String goForm() {
        return "pc/member/memberJoinForm";
    }

    @ResponseBody
    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam("phone") String phone, @RequestParam("type") String type, HttpSession session) {
        if (type.equals("join")) {
            if (!memberService.validPhoneNumber(phone)) {
                return "false";
            }
        } else if (type.equals("findIdByPhone")) {
            if (memberService.validPhoneNumber(phone)) {
                return "false";
            }
        }
        String code = authService.sendPhone(phone, "auth");
        session.setAttribute("authCode", code);
        System.out.println("code = " + code);

        return "true";
    }

    @PostMapping("/Auth")
    @ResponseBody
    public String auth(@RequestParam("code") String code, HttpSession session)  {
        String authCode = (String) session.getAttribute("authCode");
        if (authCode.equals(code)) {
            session.removeAttribute("authCode");
            return "true";
        } else {
            return "false";
        }
    }
    @PostMapping("/loginId")
    @ResponseBody
    public String sendLoginId(@RequestParam("type") String type, @RequestParam("address") String address){
        return memberService.loginId(type, address);
    }
    @PostMapping("/password")
    @ResponseBody
    public String sendPassword(@RequestParam("type") String type, @RequestParam("address") String address) {
        String code = null;
        if (type.equals("email")) {
            EmailMessage emailMessage = EmailMessage.builder()
                    .to(address)
                    .subject("[어서와yng] 임시 비밀번호 발급")
                    .build();
            code = authService.sendMail(emailMessage, "password");
        } else if (type.equals("phone")) {
            code = authService.sendPhone(address, "password");
        }
        System.out.println("type = "+type);
        memberService.updateTemp(type,address,code);

        return "true";
    }

    @ResponseBody
    @PostMapping("/email")
    public String sendAuthMail(@RequestParam("email") String email, HttpSession session, @RequestParam(name = "loginId", required = false) String loginId) {
        if(loginId==null) {
            if (memberService.validEmail(email)) {
                return "false";
            }
        }else{
            if(memberService.validEmailAndloginId(email, loginId)){
                return "false";
            }
        }
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("[어서와yng] 이메일 인증 코드")
                .build();

        String code = authService.sendMail(emailMessage, "email");
        session.setAttribute("authCode", code);
        return "true";
    }

}
