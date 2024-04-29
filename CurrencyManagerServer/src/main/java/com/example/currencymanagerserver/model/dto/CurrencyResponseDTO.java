package com.example.currencymanagerserver.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CurrencyResponseDTO {

    private String name;
    private String code;
    private List<ExchangeRateDTO> exchangeRates;
}
