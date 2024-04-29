package com.example.currencymanagerclient.handler;

import com.example.currencymanagerclient.model.dto.CurrencyRequestDTO;
import com.example.currencymanagerclient.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

@Component
@RequiredArgsConstructor
public class WebSocketHandler {

    private static final String WS_URL = "ws://localhost:8080/websocket-server";
    private static final String TOPIC = "/topic/currencies";

    private final WebSocketStompClient webSocketStompClient;
    private final CurrencyService currencyService;

    public void startClient() {
        webSocketStompClient.connectAsync(WS_URL, new StompSessionHandlerAdapter() {

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return CurrencyRequestDTO.class;
            }

            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                session.subscribe(TOPIC, this);
            }

            @SneakyThrows
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                currencyService.processCurrency((CurrencyRequestDTO) payload);
            }
        });
    }
}
