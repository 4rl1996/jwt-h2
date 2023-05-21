package com.example.jwt.notificator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "telegram-notificator",
        url = "${bot.baseUrl:}")
public interface TelegramNotificationFeign {

    @PostMapping()
    void sendNotification(@RequestBody TelegramNotificationObject notificationObject);
}
