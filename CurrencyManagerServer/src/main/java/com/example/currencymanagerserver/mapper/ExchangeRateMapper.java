package com.example.currencymanagerserver.mapper;

import com.example.currencymanagerserver.model.dto.ExchangeRateDTO;
import com.example.currencymanagerserver.model.entity.ExchangeRate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExchangeRateMapper {

    public ExchangeRateDTO mapToExchangeRateDto(ExchangeRate exchangeRate) {
        return ExchangeRateDTO.builder()
                .rate(exchangeRate.getRate())
                .reverseRate(exchangeRate.getReverseRate())
                .ratio(exchangeRate.getRatio())
                .gold(exchangeRate.getGold())
                .fStar(exchangeRate.getFStar())
                .date(exchangeRate.getDate())
                .build();
    }
}
