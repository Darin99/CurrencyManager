package com.example.currencymanagerclient.service;

import com.example.currencymanagerclient.model.dto.CurrencyRequestDTO;
import com.example.currencymanagerclient.model.entity.Currency;
import com.example.currencymanagerclient.model.entity.ExchangeRate;
import com.example.currencymanagerclient.repository.CurrencyRepository;
import com.example.currencymanagerclient.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @Override
    public void processCurrency(CurrencyRequestDTO currencyRequestDTO) {
        saveCurrencyInfo(currencyRequestDTO);
        saveExchangeRateInfo(currencyRequestDTO);
    }

    private void saveCurrencyInfo(CurrencyRequestDTO currencyRequestDTO) {
        Currency currency = currencyRepository.findCurrencyByCode(currencyRequestDTO.getCode());
        if (currency != null) {
            return;
        }
        currency = Currency.builder()
                .name(currencyRequestDTO.getName())
                .code(currencyRequestDTO.getCode())
                .build();
        currencyRepository.saveAndFlush(currency);
    }

    @SneakyThrows
    private void saveExchangeRateInfo(CurrencyRequestDTO dto) {
        double rate = dto.getRate() != null ? Double.parseDouble(dto.getRate()) : 0;
        double reverseRate = dto.getReverseRate() != null ? Double.parseDouble(dto.getReverseRate()) : 0;
        int ratio = dto.getReverseRate() != null ? Integer.parseInt(dto.getRatio()) : 0;

        Currency currency = currencyRepository.findCurrencyByCode(dto.getCode());
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findAllByCurrencyId(currency.getId());

        ExchangeRate exchangeRate = createExchangeRate(dto, rate, reverseRate, ratio, currency);

        exchangeRates.add(exchangeRate);
        exchangeRateRepository.saveAll(exchangeRates);
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
