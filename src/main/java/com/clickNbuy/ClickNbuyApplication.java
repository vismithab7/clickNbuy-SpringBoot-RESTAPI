package com.clickNbuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClickNbuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickNbuyApplication.class, args);
	}

}
