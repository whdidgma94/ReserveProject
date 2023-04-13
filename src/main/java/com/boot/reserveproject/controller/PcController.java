package com.boot.reserveproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PcController {
    @RequestMapping("/pc/index")
    public String pcIndex(){
        return "pc/index";
    }
}
