package com.adidas.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AdidasEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdidasEurekaserverApplication.class, args);
	}

}
