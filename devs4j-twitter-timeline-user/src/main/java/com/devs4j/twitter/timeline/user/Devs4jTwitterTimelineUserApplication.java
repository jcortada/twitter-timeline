package com.devs4j.twitter.timeline.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Devs4jTwitterTimelineUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(Devs4jTwitterTimelineUserApplication.class, args);
	}

}
