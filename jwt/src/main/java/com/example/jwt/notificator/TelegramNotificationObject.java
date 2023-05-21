package com.example.jwt.notificator;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TelegramNotificationObject {
    private String chat_id;
    private String text;
}
