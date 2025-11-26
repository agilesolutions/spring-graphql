package com.agilesolutions.gateway.rest;

import com.agilesolutions.gateway.domain.StockData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/time_series", accept = MediaType.APPLICATION_JSON_VALUE)
public interface StockHttpClient {

    @GetExchange
    StockData getLatestStockPrices(@RequestParam String symbol, @RequestParam String interval, @RequestParam int outputsize, @RequestParam String apikey);

    @GetExchange
    StockData getHistoricalStockPrices(@RequestParam String symbol, @RequestParam String interval, @RequestParam int outputsize, @RequestParam String apikey);

}