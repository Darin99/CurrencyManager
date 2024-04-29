package com.example.currencymanagerserver.web;

import com.example.currencymanagerserver.model.dto.CurrencyRequestDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/process-message")
    @SendTo("/topic/currencies")
    public CurrencyRequestDTO processMessage(CurrencyRequestDTO currencyRequestDTO) throws Exception {
        return currencyRequestDTO;
    }
}
