package com.karinedias.financialemailnotifier.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="api")
@Data
public class FinancialDataConfig {

    private String host;
    private String key;


}
