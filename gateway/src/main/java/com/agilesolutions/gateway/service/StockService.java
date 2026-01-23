package com.agilesolutions.gateway.service;

import com.agilesolutions.gateway.config.TwelveDataProperties;
import com.agilesolutions.gateway.domain.DailyStockData;
import com.agilesolutions.gateway.domain.StockData;
import com.agilesolutions.gateway.dto.StockDto;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class StockService {

    private final RestTemplate stockClient;

    private final TwelveDataProperties applicationProperties;

    private static final String MINUTE_INTERVAL = "1min";
    private static final String DAY_INTERVAL = "1day";

    @Observed(name = "get-latest-stock-prices",
            contextualName = "Get Latest Stock Prices",
            lowCardinalityKeyValues = {"TwelveData", "time_series"})
    public StockDto getLatestStockPrices(String company) {


        log.info("Get stock prices for: {}", company);

        StockData data = stockClient.getForObject(applicationProperties.getUrl() + "/time_series?symbol={symbol}&interval={interval}&outputsize={outputsize}&apikey={apikey}",
                StockData.class,
                company,
                MINUTE_INTERVAL,
                1,
                applicationProperties.getKey());

        DailyStockData latestData = data.getValues().get(0);
        log.info("Get stock prices ({}) -> {}", company, latestData.getClose());
        return new StockDto(Float.parseFloat(latestData.getClose()));

    }
}
