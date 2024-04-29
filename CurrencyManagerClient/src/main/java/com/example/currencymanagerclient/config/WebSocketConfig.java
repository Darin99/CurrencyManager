package com.example.currencymanagerclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
public class WebSocketConfig {

    @Bean
    public StandardWebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public WebSocketStompClient webSocketStompClient() {
        WebSocketStompClient client = new WebSocketStompClient(webSocketClient());
        client.setMessageConverter(new MappingJackson2MessageConverter());
        return client;
    }
}
