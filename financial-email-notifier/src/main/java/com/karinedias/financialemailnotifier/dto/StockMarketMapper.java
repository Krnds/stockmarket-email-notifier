package com.karinedias.financialemailnotifier.dto;

import com.karinedias.financialemailnotifier.model.StockMarket;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface StockMarketMapper {

    StockMarket dtoToObject (StockMarketDataDTO stockMarketDataDTO);

}
