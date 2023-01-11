package com.devs4j.twitter.timeline.user.service;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class Discovery implements CommandLineRunner {

	
	private static final Logger log = LoggerFactory.getLogger(Discovery.class);

	
	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public void run(String... args) throws Exception {
		discoveryClient.getInstances("tweets-feed").forEach((ServiceInstance s) -> {
            log.info("client: " +ToStringBuilder.reflectionToString(s));
        });		
	}
}
