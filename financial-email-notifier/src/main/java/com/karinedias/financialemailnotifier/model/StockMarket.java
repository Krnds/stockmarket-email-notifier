package com.karinedias.financialemailnotifier.model;

import com.karinedias.financialemailnotifier.dto.StockMarketMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMarket {

    @Autowired
    StockMarketMapper stockMarketMapper;

    public LocalDateTime date;
    public double high;
    public double low;

    @Override
    public String toString() {
        return "StockMarket{" +
                "date=" + date +
                ", high=" + high +
                ", low=" + low +
                '}';
    }

}
