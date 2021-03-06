package com.company.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaProviderOutsideApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProviderOutsideApplication.class, args);
	}

}
