package com.example.currencymanagerserver.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ExchangeRateDTO {

    private Double rate;
    private Double reverseRate;
    private Integer ratio;
    private Integer gold;
    private Integer fStar;
    private LocalDate date;
}
