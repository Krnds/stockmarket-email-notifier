package com.karinedias.financialemailnotifier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karinedias.financialemailnotifier.dto.StockMarketDTO;
import com.karinedias.financialemailnotifier.dto.StockMarketDataDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FinancialDataService {

    @Autowired
    FinancialDataConfig config;

    StockMarketDTO stockMarketDTO;

    private OkHttpClient client = new OkHttpClient();

    private static final String ONE_DAY = "1d";
    private static final String ONE_MONTH = "30d";

    public StockMarketDTO getStockMinAndMaxForDay(String stockSymbol) {
        Request request = getHTTPRequestForStockSymbol(stockSymbol, ONE_DAY);
        try (Response response = client.newCall(request).execute()) {
            ObjectMapper objectMapper = new ObjectMapper();
            stockMarketDTO = objectMapper.readValue(response.body().string(), StockMarketDTO.class);
        } catch (Exception e) {
            log.error("Error in object : " + e.getMessage());
        }
        return stockMarketDTO;
    }

    public double getStockMinValueFromLastMonth(String stockSymbol) {
        Request request = getHTTPRequestForStockSymbol(stockSymbol, ONE_MONTH);
        double minValue = 0D;
        try (Response response = client.newCall(request).execute()) {
            ObjectMapper objectMapper = new ObjectMapper();
            StockMarketDTO sm = objectMapper.readValue(response.body().string(), StockMarketDTO.class);
            List<StockMarketDataDTO> stocksFromLastMonth = sm.getData();
            minValue = stocksFromLastMonth.stream().mapToDouble(s -> s.getLow()).min().orElseThrow(NoSuchFieldException::new);
        } catch (Exception e) {
            log.error("Error in object : " + e.getMessage());
        }
        return minValue;
    }


    private Request getHTTPRequestForStockSymbol (String stockSymbol, String period) {
        RequestBody body = new FormBody.Builder().add("symbol", stockSymbol).add("period", period).build();
        Request request = new Request.Builder().url("https://" + config.getHost() + "/price").post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("X-RapidAPI-Key", config.getKey())
                .addHeader("X-RapidAPI-Host", config.getHost()).build();
        return request;
    }
}
