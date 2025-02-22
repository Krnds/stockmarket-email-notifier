package com.karinedias.financialemailnotifier;

import com.karinedias.financialemailnotifier.model.StockMarket;
import com.karinedias.financialemailnotifier.service.FinancialDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandLineApp implements CommandLineRunner {

  private final FinancialDataService financialDataService;
  private final String stockSymbol;

  public CommandLineApp(
      FinancialDataService financialDataService,
      @Value("${app.stock-symbol}") String stockSymbol) {
    this.financialDataService = financialDataService;
    this.stockSymbol = stockSymbol;
  }

  @Override
  public void run(String... args) {

    StockMarket stockMarket = financialDataService.getStockMarketValue(stockSymbol);
    if (stockMarket != null) {
      log.info("Stock market value: {}", stockMarket);
    } else {
      log.error("Failed to retrieve stock market data for symbol: {}", stockSymbol);
    }
  }

}
