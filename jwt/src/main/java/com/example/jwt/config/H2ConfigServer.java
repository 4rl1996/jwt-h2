package com.example.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * Оборачивается embeded H2-БД в tcp-сервер
 * В Идее можем подключиться так: Database вкладка-> Datasource:H2-> ConnectionType:Remote-> localhost/port:9092 -> Database: mem:testdb
 * В браузе по localhost:8082
 */
@Configuration
public class H2ConfigServer {

    private org.h2.tools.Server webServer;
    private org.h2.tools.Server tcpServer;

    @EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8082", "-tcpAllowOthers").start();
        this.tcpServer = org.h2.tools.Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void stop() {
        this.tcpServer.stop();
        this.webServer.stop();
    }

}
