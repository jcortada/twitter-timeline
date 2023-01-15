package com.devs4j.twitter.timeline.user;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class Devs4jTwitterTimelineUserApplication implements CommandLineRunner {

	@Autowired
	RedissonClient client;
	
	public static void main(String[] args) {
		SpringApplication.run(Devs4jTwitterTimelineUserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		RBucket<Object> bucket = client.getBucket("translation");
		bucket.set("sdfsdfdsf");
		
		
	}

}
