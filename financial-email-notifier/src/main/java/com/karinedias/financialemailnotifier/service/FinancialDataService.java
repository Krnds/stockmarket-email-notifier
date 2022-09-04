package com.karinedias.financialemailnotifier.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.karinedias.financialemailnotifier.model.Data;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FinancialDataService {

    OkHttpClient client = new OkHttpClient();
    Dotenv dotenv = Dotenv.configure().filename(".env").load();
    String url = dotenv.get("API_HOST");
    String apikey = dotenv.get("API_KEY");


    public String run() throws IOException {

        RequestBody body = new FormBody.Builder().add("symbol", "CW8.PA").add("period", "1d").build();
        Request request = new Request.Builder().url("https://" + url + "/price").post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("X-RapidAPI-Key", apikey)
                .addHeader("X-RapidAPI-Host", url).build();
        try (Response response = client.newCall(request).execute()) {
            ObjectMapper objectMapper = new ObjectMapper();
            return response.body().toString();
        }

    }
}
