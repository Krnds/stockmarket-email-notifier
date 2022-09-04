package com.karinedias.financialemailnotifier;

import com.karinedias.financialemailnotifier.model.Data;
import com.karinedias.financialemailnotifier.service.FinancialDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FinancialEmailNotifierApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FinancialEmailNotifierApplication.class, args);
		FinancialDataService fds = new FinancialDataService();
		String result = fds.run();
		System.out.println(result);
	}

}
