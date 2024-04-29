package com.example.currencymanagerserver.service;

import com.example.currencymanagerserver.mapper.CurrencyMapper;
import com.example.currencymanagerserver.model.dto.CurrencyRequestDTO;
import com.example.currencymanagerserver.model.dto.CurrencyResponseDTO;
import com.example.currencymanagerserver.model.entity.Currency;
import com.example.currencymanagerserver.model.entity.ExchangeRate;
import com.example.currencymanagerserver.repository.CurrencyRepository;
import com.example.currencymanagerserver.repository.ExchangeRateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<CurrencyResponseDTO> processCurrencies(List<CurrencyRequestDTO> currencyRequestDTOS) {
        return currencyRequestDTOS.stream()
                .skip(1)
                .map(this::saveCurrencyInfo)
                .map(this::saveExchangeRateInfo)
                .toList()
                .stream().map(currency -> {
                    List<ExchangeRate> exchangeRates = exchangeRateRepository.findAllByCurrencyId(currency.getId());
                    return CurrencyMapper.mapToCurrencyResponseDTO(currency, exchangeRates);
                })
                .toList();
    }

    private CurrencyRequestDTO saveCurrencyInfo(CurrencyRequestDTO currencyRequestDTO) {
        Currency currency = currencyRepository.findCurrencyByCode(currencyRequestDTO.getCode());
        if (currency != null) {
            return currencyRequestDTO;
        }
        currency = Currency.builder()
                .name(currencyRequestDTO.getName())
                .code(currencyRequestDTO.getCode())
                .build();
        currencyRepository.saveAndFlush(currency);
        return currencyRequestDTO;
    }

    @SneakyThrows
    private Currency saveExchangeRateInfo(CurrencyRequestDTO dto) {
        double rate = dto.getRate() != null ? Double.parseDouble(dto.getRate()) : 0;
        double reverseRate = dto.getReverseRate() != null ? Double.parseDouble(dto.getReverseRate()) : 0;
        int ratio = dto.getReverseRate() != null ? Integer.parseInt(dto.getRatio()) : 0;

        Currency currency = currencyRepository.findCurrencyByCode(dto.getCode());
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findAllByCurrencyId(currency.getId());

        ExchangeRate exchangeRate = createExchangeRate(dto, rate, reverseRate, ratio, currency);

        if (!exchangeRates.isEmpty()) {
            ExchangeRate lastExRate = exchangeRates.get(exchangeRates.size() - 1);
            if (rate != lastExRate.getRate() ||
                    reverseRate != lastExRate.getReverseRate() ||
                    ratio != lastExRate.getRatio()) {
                exchangeRates.add(exchangeRate);
                exchangeRateRepository.saveAll(exchangeRates);
                messagingTemplate.convertAndSend("/topic/currencies", objectMapper.writeValueAsString(dto));
            }
        } else {
            exchangeRates.add(exchangeRate);
            exchangeRateRepository.saveAll(exchangeRates);
            messagingTemplate.convertAndSend("/topic/currencies", objectMapper.writeValueAsString(dto));
        }
        return currency;
    }

    private ExchangeRate createExchangeRate(CurrencyRequestDTO currencyRequestDTO,
                                            double rate,
                                            double reverseRate,
                                            int ratio,
                                            Currency currency) {
        return ExchangeRate.builder()
                .rate(rate)
                .reverseRate(reverseRate)
                .ratio(ratio)
                .date(LocalDate.parse(currencyRequestDTO.getDate(), FORMATTER))
                .gold(currencyRequestDTO.getGold())
                .fStar(currencyRequestDTO.getFStar())
                .currency(currency)
                .build();
    }
}
