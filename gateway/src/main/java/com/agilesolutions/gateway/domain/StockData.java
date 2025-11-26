package com.agilesolutions.gateway.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StockData {

    private List<DailyStockData> values;

}