package com.boot.reserveproject.config;

import com.boot.reserveproject.domain.MyJavaMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;


@Configuration
public class AppConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        return new MyJavaMailSender();
    }
}
