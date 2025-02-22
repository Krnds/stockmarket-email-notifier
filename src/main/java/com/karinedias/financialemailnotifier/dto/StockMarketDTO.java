package com.karinedias.financialemailnotifier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockMarketDTO {

  @JsonProperty("body.regularMarketPrice")
  private BigDecimal currentPrice;

  @JsonProperty("body.currency")
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  private Currency currency;

  public static class StringToCurrencyConverter extends StdConverter<String, Currency> {
    @Override
    public Currency convert(String value) {
      return Currency.getInstance(value);
    }
  }
}


