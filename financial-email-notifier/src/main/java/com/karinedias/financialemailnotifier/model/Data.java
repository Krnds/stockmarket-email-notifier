package com.karinedias.financialemailnotifier.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
public class Data {

    @JsonProperty("Close")
    public double close;
    @JsonProperty("Date")
    public long date;
    @JsonProperty("Dividends")
    public int dividends;
    @JsonProperty("High")
    public double high;
    @JsonProperty("Low")
    public double low;
    @JsonProperty("Open")
    public double open;
    @JsonProperty("Stock Splits")
    public int stockSplits;
    @JsonProperty("Volume")
    public int volume;
}
