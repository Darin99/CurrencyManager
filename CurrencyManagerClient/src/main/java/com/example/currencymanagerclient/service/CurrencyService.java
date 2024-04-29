package com.example.currencymanagerclient.service;

import com.example.currencymanagerclient.model.dto.CurrencyRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;


@Validated
public interface CurrencyService {

    void processCurrency(@Valid CurrencyRequestDTO currencies) throws JsonProcessingException;
}
