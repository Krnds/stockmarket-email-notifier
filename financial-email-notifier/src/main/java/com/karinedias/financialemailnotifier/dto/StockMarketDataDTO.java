package com.karinedias.financialemailnotifier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.karinedias.financialemailnotifier.util.EpochToLocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockMarketDataDTO {

    @JsonDeserialize(using = EpochToLocalDateTimeDeserializer.class)
    @JsonProperty("Date")
    public LocalDateTime date;
    @JsonProperty("High")
    public Float high;
    @JsonProperty("Low")
    public Float low;

    @Override
    public String toString() {
        return "As of " + formatLocalDateTime(date) + " your stock market fund has a highest value of " + high + "€ and a lowest value of "
                + low + "€.";
    }

    private String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return localDateTime.format(formatter);
    }
}
