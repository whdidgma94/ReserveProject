package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class memberControllerTest {
    MemberService memberService;
    @Test
    void 테스트(){
        Member m = memberService.selectMemberById("123124");
        System.out.println( m.getCampLikes().getClass());

    }
}
