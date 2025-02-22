package com.karinedias.financialemailnotifier.client;

import com.karinedias.financialemailnotifier.dto.StockMarketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stockMarket", url = "${spring.api.host}")
public interface StockMarketClient {

  @GetMapping("/api/v1/markets/stock/quotes")
  StockMarketDTO getStockMarketQuote(
      @RequestParam("ticker") String stockSymbol,
      @RequestHeader("X-RapidAPI-Key") String apiKey,
      @RequestHeader("X-RapidAPI-Host") String apiHost
  );
}