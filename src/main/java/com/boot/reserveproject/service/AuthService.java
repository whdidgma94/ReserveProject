package com.boot.reserveproject.service;


import com.boot.reserveproject.domain.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final MemberService memberService;

    public String sendPhone(String phone, String type) {
        String api_key = "NCSPKXA0I2T4OKRX";
        String api_secret = "HYOAKFIG9QMULUXQDKSCRZMROULK0GZI";

        Message sms = new Message(api_key, api_secret);
        String code = createCode();
        String msg = "";
        if (type.equals("password")) {
            msg = "[어서y]임시 비밀번호는 [" + code + "] 입니다";
        } else {
            msg = "[어서y]인증번호는 [" + code + "] 입니다";

        }
        System.out.println("phone="+phone);
        HashMap<String, String> params = new HashMap<>();
        params.put("to", phone);
        params.put("from", "010-4134-2824");
        params.put("type", "SMS");
        params.put("text", msg);
        params.put("app_version", "test app 1.2");

//            try {
//                JSONObject obj = sms.send(params);
//                System.out.println("obj="+ obj.toString());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                System.out.println(e.getCode());
//            }
        return code;
    }

    public String sendMail(EmailMessage emailMessage, String type) {
        String authNum = createCode();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        if (type.equals("password")) memberService.updateTemp("email", emailMessage.getTo(), authNum);

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
            mimeMessageHelper.setText(setContext(authNum, type), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("Success");

            return authNum;

        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }
    }

    // 인증번호 및 임시 비밀번호 생성 메서드
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0:
                    key.append((char) ((int) random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65));
                    break;
                default:
                    key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

    // thymeleaf를 통한 html 적용
    public String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
}