package com.example.currencymanagerserver.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate extends BaseEntity {

    @Min(value = 0, message = "number must be positive")
    private Double rate;

    @Min(value = 0, message = "number must be positive")
    private Double reverseRate;

    @Min(value = 0, message = "number must be positive")
    private Integer ratio;

    @Min(value = 0, message = "number must be positive")
    private int gold;

    @Min(value = 0, message = "number must be positive")
    private int fStar;

    private LocalDate date;

    @ManyToOne
    private Currency currency;
}
