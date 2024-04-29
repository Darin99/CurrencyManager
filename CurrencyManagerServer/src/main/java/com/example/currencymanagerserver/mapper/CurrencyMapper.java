package com.example.currencymanagerserver.mapper;

import com.example.currencymanagerserver.model.dto.CurrencyResponseDTO;
import com.example.currencymanagerserver.model.dto.ExchangeRateDTO;
import com.example.currencymanagerserver.model.entity.Currency;
import com.example.currencymanagerserver.model.entity.ExchangeRate;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CurrencyMapper {

    public CurrencyResponseDTO mapToCurrencyResponseDTO(Currency currency, List<ExchangeRate> exchangeRates) {
        List<ExchangeRateDTO> exchangeRateDTOS = new ArrayList<>();
        for (ExchangeRate exchangeRate : exchangeRates) {
            exchangeRateDTOS.add(ExchangeRateMapper.mapToExchangeRateDto(exchangeRate));
        }

        return CurrencyResponseDTO.builder()
                .name(currency.getName())
                .code(currency.getCode())
                .exchangeRates(exchangeRateDTOS)
                .build();
    }
}
