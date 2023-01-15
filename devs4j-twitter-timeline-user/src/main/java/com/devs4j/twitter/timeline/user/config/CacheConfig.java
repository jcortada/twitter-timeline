package com.devs4j.twitter.timeline.user.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

	//@Value("${redis.address}")
	private String redisAddress;

/*	
	//@Bean(destroyMethod = "shutdown")
	public RedissonClient redisson() {
		Config config = new Config();
		config.useSingleServer().setAddress(redisAddress);
		return Redisson.create(config);
	}

	//@Bean
	public CacheManager getManager(RedissonClient redissonClient) {
		Map<String, CacheConfig> config = new HashMap<>();
		config.put("twitter.timeline", new CacheConfig());
		return new RedissonSpringCacheManager(redissonClient);

	}
*/
	
	//@Bean
	/*
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(60))
				.disableCachingNullValues()
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}*/
	
	
	@Bean(destroyMethod = "shutdown")
	public RedissonClient redisson() throws IOException {
		/*
		Config config = new Config();
		config.useSingleServer().setAddress(redisAddress);
		config.setCodec(new JsonJacksonCodec());
		*/
		
		InputStream ioStream = this.getClass()
		        .getClassLoader()
		        .getResourceAsStream("config-file.yaml");
		
		Config config = Config.fromYAML(ioStream);  
		//Config config = Config.fromYAML(new File("classpath:/application.yml"));  

		return Redisson.create(config);
}

	@Bean
	public CacheManager getManager(RedissonClient redissonClient) {
		Map<String, CacheConfig> config = new HashMap<>();
		config.put("translations", new CacheConfig());
		//config.put("user.timeline", new CacheConfig());
		return new RedissonSpringCacheManager(redissonClient);

	}
	
}
