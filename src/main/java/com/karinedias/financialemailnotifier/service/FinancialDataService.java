package com.karinedias.financialemailnotifier.service;

import com.karinedias.financialemailnotifier.client.StockMarketClient;
import com.karinedias.financialemailnotifier.config.FinancialDataConfig;
import com.karinedias.financialemailnotifier.dto.StockMarketDTO;
import com.karinedias.financialemailnotifier.dto.StockMarketMapper;
import com.karinedias.financialemailnotifier.model.StockMarket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FinancialDataService {

  private final FinancialDataConfig dataConfig;
  private final StockMarketMapper mapper;
  private final StockMarketClient stockMarketClient;

  public FinancialDataService(FinancialDataConfig dataConfig,
      StockMarketMapper mapper,
      StockMarketClient stockMarketClient) {
    this.dataConfig = dataConfig;
    this.mapper = mapper;
    this.stockMarketClient = stockMarketClient;
  }

  public StockMarket getStockMarketValue(String stockSymbol) {
    try {
      StockMarketDTO stockMarketDTO = stockMarketClient.getStockMarketQuote(
          stockSymbol,
          dataConfig.getKey(),
          dataConfig.getHost()
      );
      return mapper.dtoToObject(stockMarketDTO);
    } catch (Exception e) {
      log.error("Error fetching stock market data: {}", e.getMessage());
      return null;
    }
  }
}
