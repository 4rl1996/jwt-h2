package com.example.jwt.controller;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {

    @PostMapping
    void sendMessage() throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.rambler.ru");
        email.setSmtpPort(2525);
        email.setAuthentication("notification.hack@rambler.ru", "123H@ck345");
        email.setFrom("notification.hack@rambler.ru");
        email.setSubject("Проверка почты");
        email.addTo("umnikroma@bk.ru");
        email.setHtmlMsg("</head><h3>Salam</h3>\nЭто тест отправки почты");
        email.setCharset("UTF-8");
        String send = email.send();
        System.out.printf(send);
    }
}
