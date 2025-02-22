package com.karinedias.financialemailnotifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinancialEmailNotifierApplication {

  public static void main(String[] args) {
    SpringApplication.run(FinancialEmailNotifierApplication.class, args);
  }
}
