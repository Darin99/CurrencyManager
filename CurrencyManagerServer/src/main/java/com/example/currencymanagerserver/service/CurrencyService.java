package com.example.currencymanagerserver.service;

import com.example.currencymanagerserver.model.dto.CurrencyRequestDTO;
import com.example.currencymanagerserver.model.dto.CurrencyResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CurrencyService {

    List<CurrencyResponseDTO> processCurrencies(@Valid List<CurrencyRequestDTO> currencies) throws JsonProcessingException;
}
