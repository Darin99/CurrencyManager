package com.example.currencymanagerclient;

import com.example.currencymanagerclient.handler.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CurrencyManagerClientApplication implements ApplicationRunner {

    private final WebSocketHandler webSocketHandler;

    public static void main(String[] args) {
        SpringApplication.run(CurrencyManagerClientApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        webSocketHandler.startClient();
    }
}
