package com.karinedias.financialemailnotifier.model;

import java.math.BigDecimal;
import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMarket {

  BigDecimal value;
  Currency currency;

}
