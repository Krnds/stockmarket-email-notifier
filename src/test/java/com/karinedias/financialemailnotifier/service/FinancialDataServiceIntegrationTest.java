package com.karinedias.financialemailnotifier.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.karinedias.financialemailnotifier.model.StockMarket;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FinancialDataServiceIntegrationTest {

  @Autowired
  private FinancialDataService financialDataService;

  @MockBean
  private OkHttpClient okHttpClient;

  @Test
  void shouldMapJsonResponseToStockMarket() throws IOException {
    // Given
    String jsonContent = new String(
        Objects.requireNonNull(getClass().getResourceAsStream("/data.json")).readAllBytes());

    Response mockResponse = new Response.Builder()
        .request(new Request.Builder().url("http://test.com").build())
        .protocol(Protocol.HTTP_1_1)
        .code(200)
        .message("OK")
        .body(ResponseBody.create(jsonContent, MediaType.parse("application/json")))
        .build();

    Call mockCall = mock(Call.class);
    when(mockCall.execute()).thenReturn(mockResponse);
    when(okHttpClient.newCall(any())).thenReturn(mockCall);

    // When
    StockMarket result = financialDataService.getStockMarketValue("AAPL");

    // Then
    assertThat(result).isNotNull();
    assertThat(result.getValue()).isEqualTo(new BigDecimal("245.55"));
    assertThat(result.getCurrency()).isEqualTo(Currency.getInstance("USD"));
  }
}
