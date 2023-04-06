package com.boot.reserveproject.controller;

import com.boot.reserveproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class SmsController {
    private final MemberService memberService;
    private final HttpServletRequest request;
    @PostMapping("/sendMsg")
    public boolean smsSend() {
        String phone = request.getParameter("phoneNumber");
        if (!memberService.validPhoneNumber(phone)) {
            return false;
        }
        String api_key = "NCSPKXA0I2T4OKRX";
        String api_secret = "HYOAKFIG9QMULUXQDKSCRZMROULK0GZI";

        Message sms = new Message(api_key, api_secret);
        Random rd = new Random();
        String numStr = "";

        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rd.nextInt(10));
            numStr += ran;
        }

        String msg = "[어서y]인증번호는 [" + numStr + "] 입니다";

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phone);
        params.put("from", "010-4134-2824");
        params.put("type", "SMS");
        params.put("text", msg);
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = sms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        HttpSession session = request.getSession();
        session.setAttribute("rd", numStr);
        return true;
    }

    @PostMapping("/phoneAuth")
    @ResponseBody
    public Boolean phoneAuth() {
        HttpSession session = request.getSession();
        String rd = (String) session.getAttribute("rd");
        String code = request.getParameter("code");

        System.out.println(rd + " : " + code);

        if (rd.equals(code)) {
            session.removeAttribute("rd");
            return true;
        }

        return false;
    }
}
