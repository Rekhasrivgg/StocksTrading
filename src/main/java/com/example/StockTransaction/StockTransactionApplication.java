package com.example.StockTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.app.stock.controller", "com.app.stock.service",
		"com.app.stock.globalexceptionhandlers" })
@EnableJpaRepositories("com.app.stock.repositories")
@EntityScan("com.app.stock.entities")
public class StockTransactionApplication {

	public static Logger lg = LoggerFactory.getLogger(StockTransactionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StockTransactionApplication.class, args);
		lg.debug("Entered");
		System.out.println("Hello");
		lg.debug("Exit");
	}

}
