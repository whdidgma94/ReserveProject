package com.boot.reserveproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController {
    @GetMapping("/get_session")
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println("1");
        if(session.getAttribute("log")==null){
            return "false";
        }else {
        System.out.println(session.getAttribute("log"));
            String log = (String) session.getAttribute("log");
            if(log.equals("admin")){
                return "true";
            }else{
                return "false";
            }
        }
    }
}
