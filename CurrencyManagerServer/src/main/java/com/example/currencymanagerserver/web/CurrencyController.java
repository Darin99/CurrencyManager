package com.example.currencymanagerserver.web;

import com.example.currencymanagerserver.model.dto.CurrenciesDTO;
import com.example.currencymanagerserver.model.dto.CurrencyResponseDTO;
import com.example.currencymanagerserver.service.CurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/download-currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private static final String DOWNLOAD_CURRENCIES_URL =
            "https://www.bnb.bg/Statistics/StExternalSector/StExchangeRates/StERForeignCurrencies/index.htm?download=xml&search=&lang=BG";

    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CurrencyResponseDTO>> getCurrencies() throws JsonProcessingException {
        CurrenciesDTO currenciesDTO = restTemplate.getForObject(DOWNLOAD_CURRENCIES_URL, CurrenciesDTO.class);
        List<CurrencyResponseDTO> currencyResponseDTOS = currencyService
                .processCurrencies(Objects.requireNonNull(currenciesDTO).getCurrencies());
        return ResponseEntity.ok(currencyResponseDTOS);
    }
}
