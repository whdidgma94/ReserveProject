package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.EmailMessage;
import com.boot.reserveproject.service.AuthService;
import com.boot.reserveproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        }else if(type.equals("findIdByPhone")){
            if (memberService.validPhoneNumber(phone)) {
                return "false";
            }
        }
        String code = authService.sendPhone(phone, "auth");
        session.setAttribute("authCode", code);
        System.out.println("authCode = " + code);
        return "true";
    }

    @PostMapping("/Auth")
    @ResponseBody
    public String auth(@RequestParam("code") String code, HttpSession session) throws IOException {
        String authCode = (String) session.getAttribute("authCode");
        System.out.println("2code = " + code);
        System.out.println("2authCode = " + authCode);
        if (authCode.equals(code)) {
            session.removeAttribute("authCode");
            return "true";
        } else {
            return "false";
        }
    }
    @ResponseBody
    @PostMapping("/password")
    public String sendPassword(@RequestParam("type") String type, @RequestParam("address") String address) {
        if(type.equals("email")){
            if(memberService.validEmail(address)){
                return "false";
            }
            EmailMessage emailMessage = EmailMessage.builder()
                    .to(address)
                    .subject("[어서y] 임시 비밀번호 발급")
                    .build();
        authService.sendMail(emailMessage, "password");
        }else if(type.equals("phone")){
            if (memberService.validPhoneNumber(address)) {
                return "false";
            }
            authService.sendPhone(address, "password");
        }



        return "true";
    }
    @ResponseBody
    @PostMapping("/email")
    public String sendAuthMail(@RequestParam("email") String email, HttpSession session) {
        if(memberService.validEmail(email)){
            return "false";
        }
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("[어서y] 이메일 인증 코드")
                .build();

        String code = authService.sendMail(emailMessage, "email");
        session.setAttribute("authCode", code);
        return "true";
    }

}
