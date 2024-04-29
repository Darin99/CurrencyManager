package com.example.currencymanagerclient.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency")
public class Currency extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Code is mandatory")
    private String code;

    @OneToMany(mappedBy = "currency")
    private List<ExchangeRate> exchangeRates;
}
