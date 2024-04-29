package com.example.currencymanagerclient.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRequestDTO {

    @Min(value = 0, message = "number must be positive")
    private int gold;

    @Min(value = 0, message = "number must be positive")
    private int fStar;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Code is mandatory")
    private String code;

    private String extraInfo;
    private String title;
    private String ratio;
    private String reverseRate;
    private String rate;
    private String date;
}
