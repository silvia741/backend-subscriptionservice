package com.adidas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AdidasEmailserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdidasEmailserviceApplication.class, args);
	}

}
