package com.karinedias.financialemailnotifier;

import com.karinedias.financialemailnotifier.dto.StockMarketDTO;
import com.karinedias.financialemailnotifier.dto.StockMarketDataDTO;
import com.karinedias.financialemailnotifier.email.MailBuilder;
import com.karinedias.financialemailnotifier.email.MailConfig;
import com.karinedias.financialemailnotifier.service.FinancialDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CommandLineApp implements CommandLineRunner {

    @Autowired
    MailBuilder mailBuilder;

    @Autowired
    MailConfig mailConfig;

    @Autowired
    FinancialDataService financialDataService;

    @Override
    public void run(String... args) {
        String stockMarketSymbol = "CW8.PA";
        StockMarketDTO stockMarketResult = financialDataService.getStockMinAndMaxForDay(stockMarketSymbol);
        double minimumStockValueFromLastMonth = financialDataService.getStockMinValueFromLastMonth(stockMarketSymbol);
        List<StockMarketDataDTO> result = stockMarketResult.getData();
        String mailContent = result + " .The minimum value of your stock in the last 30 days was " + String.format("%.2f", minimumStockValueFromLastMonth) + " €";
        mailBuilder.send("financial-email@notifier.com", mailConfig.getUsername(), "[Financial email notifier] Daily Update \uD83D\uDCEC", mailContent);
    }

}
