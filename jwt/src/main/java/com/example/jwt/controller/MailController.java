package com.example.jwt.controller;

import com.example.jwt.notificator.TelegramNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final JavaMailSenderImpl javaMailSender;
    private final TelegramNotificationService notificationService;

    @PostMapping
    void sendMessage() throws MessagingException, UnsupportedEncodingException {

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
        mimeMessage.setContent(multipart);
        javaMailSender.send(mimeMessage);
        notificationService.sendMessage("Email successfully sent");
    }

    private File getFile() {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource("images/roma.jpg")).getFile());
    }
}
