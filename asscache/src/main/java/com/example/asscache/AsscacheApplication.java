package com.example.asscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AsscacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsscacheApplication.class, args);
	}

}
