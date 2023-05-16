package com.example.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final JavaMailSenderImpl javaMailSender;
    private final MailSender m;

    @PostMapping
    void sendMessage() throws MessagingException, UnsupportedEncodingException {
//        HtmlEmail email = new HtmlEmail();
//        email.setHostName("smtp.rambler.ru");
//        email.setSmtpPort(2525);
//        email.setAuthentication("notification.hack@rambler.ru", "123H@ck345");
//        email.setFrom("notification.hack@rambler.ru");
//        email.setSubject("Проверка почты");
//        email.addTo("umnikroma@bk.ru");
//        email.setHtmlMsg("</head><h3>Salam</h3>\nЭто тест отправки почты");
//        email.setCharset("UTF-8");
//        String send = email.send();
//        System.out.println(send);
//        System.out.println(email.getSmtpPort());
//        System.out.println(email.getSslSmtpPort());
//        MailSender m = new JavaMailSenderImpl();
//        SimpleMailMessage simpleMail = new SimpleMailMessage();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMultipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<H1>Привет!</H1>На связи Роман из команды <i>ProblemSolvers</i><br><b>Получилось сделать работу с разметкой?</b><br><br>Это я отмечаю выход в финал:<img src=\"cid:image\">";
        messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
// add it
        multipart.addBodyPart(messageBodyPart);

        // second part (the image)
        messageBodyPart = new MimeBodyPart();
        File file = getFile();
        DataSource fds = new FileDataSource(file);

        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");
        multipart.addBodyPart(messageBodyPart);


        mimeMessage.setFrom(new InternetAddress("nrh9@yandex.ru", "Рома из команды ProblemSolvers"));
        mimeMessage.setSubject("Good day, чемпион!");
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("umnikroma@bk.ru"));
//        simpleMail.setTo("umnikroma@bk.ru", "4rl1996@gmail.com", "Kristiane.light@gmail.com", "itikineva@yandex.ru");
//        mimeMailMessage.setTo("umnikroma@bk.ru", "4rl1996@gmail.com", "Kristiane.light@gmail.com", "itikineva@yandex.ru");
//        mimeMessage.setTo("umnikroma@bk.ru", "4rl1996@gmail.com");
//        simpleMail.setSubject("Good day, чемпион!");
//        mimeMailMessage.setSubject("Good day, чемпион!");
//        simpleMail.setText("Привет!\nЭто Рома из команды ProblevSolvers");
//        mimeMessage.setContent("<H1>Привет!</H1>\nЭто Рома из команды <i>ProblemSolvers</i>\n\n<b>Получилось сделать работу с разметкой?</b>", "text/html; charset=utf-8");
        mimeMessage.setContent(multipart);
//        m.send(simpleMail);
        javaMailSender.send(mimeMessage);
    }

    private File getFile() {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource("images/roma.jpg")).getFile());
    }
}
