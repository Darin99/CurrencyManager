package com.example.currencymanagerserver.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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
@JacksonXmlRootElement(localName = "ROW")
public class CurrencyRequestDTO {

    @JacksonXmlProperty(localName = "GOLD")
    @Min(value = 0, message = "number must be positive")
    private int gold;

    @JacksonXmlProperty(localName = "F_STAR")
    @Min(value = 0, message = "number must be positive")
    private int fStar;

    @JacksonXmlProperty(localName = "NAME_")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @JacksonXmlProperty(localName = "CODE")
    @NotBlank(message = "Code is mandatory")
    private String code;

    @JacksonXmlProperty(localName = "EXTRAINFO")
    private String extraInfo;

    @JacksonXmlProperty(localName = "TITLE")
    private String title;

    @JacksonXmlProperty(localName = "RATIO")
    private String ratio;

    @JacksonXmlProperty(localName = "REVERSERATE")
    private String reverseRate;

    @JacksonXmlProperty(localName = "RATE")
    private String rate;

    @JacksonXmlProperty(localName = "CURR_DATE")
    private String date;
}
