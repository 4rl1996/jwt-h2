package com.example.jwt.notificator;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramNotificationService {

    @Value("${bot.baseUrl:}")
    private String apiUrl;

    @Value("${bot.chat}")
    private String chatId;

    private final TelegramNotificationFeign notifyFeignClient;

    public void sendMessage(String text) {
        TelegramNotificationObject notificationObject = TelegramNotificationObject.builder()
                .chat_id(chatId)
                .text(text)
                .build();
        notifyFeignClient.sendNotification(notificationObject);
    }
}
