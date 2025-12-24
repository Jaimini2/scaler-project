package com.example.ProductServiceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceAppApplication.class, args);
	}

}
