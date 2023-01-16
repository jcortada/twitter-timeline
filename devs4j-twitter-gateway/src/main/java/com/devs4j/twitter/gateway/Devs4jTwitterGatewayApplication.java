package com.devs4j.twitter.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class Devs4jTwitterGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Devs4jTwitterGatewayApplication.class, args);
	}

}
