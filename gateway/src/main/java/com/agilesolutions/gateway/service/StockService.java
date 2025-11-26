package com.agilesolutions.gateway.service;

import com.agilesolutions.gateway.config.ApplicationProperties;
import com.agilesolutions.gateway.domain.DailyStockData;
import com.agilesolutions.gateway.domain.StockData;
import com.agilesolutions.gateway.dto.StockDto;
import com.agilesolutions.gateway.rest.StockHttpClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StockService {

    private final StockHttpClient stockClient;

    private final ApplicationProperties applicationProperties;

    private static final String MINUTE_INTERVAL = "1min";
    private static final String DAY_INTERVAL = "1day";

    public StockDto getLatestStockPrices(String company) {

        log.info("Get stock prices for: {}", company);
        StockData data = stockClient.getLatestStockPrices(company, MINUTE_INTERVAL, 1, applicationProperties.getKey());
        DailyStockData latestData = data.getValues().get(0);
        log.info("Get stock prices ({}) -> {}", company, latestData.getClose());
        return new StockDto(Float.parseFloat(latestData.getClose()));

    }
}
