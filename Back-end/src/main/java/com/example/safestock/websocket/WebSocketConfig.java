package com.example.safestock.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final AlertaWebSocketHandler alertaWebSocketHandler;

    public WebSocketConfig(AlertaWebSocketHandler alertaWebSocketHandler) {
        this.alertaWebSocketHandler = alertaWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(alertaWebSocketHandler, "/ws/alertas")
                .setAllowedOriginPatterns("*")
                .addInterceptors(new AuthHandshakeInterceptor());
    }
}