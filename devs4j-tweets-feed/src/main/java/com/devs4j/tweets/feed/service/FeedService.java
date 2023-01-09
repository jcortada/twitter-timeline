package com.devs4j.tweets.feed.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.devs4j.tweets.feed.model.dto.TweetDto;
import com.devs4j.tweets.feed.model.entity.TweetEntity;
import com.devs4j.tweets.feed.repository.TweetRepository;

@Service
public class FeedService {
	private static String topic = "tweetBinding-out-0";

	@Autowired
	private TweetRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StreamBridge streamBridge;
	
	public TweetDto save(TweetDto tweetDto) {
		TweetEntity tweetEntity = mapper.map(tweetDto, TweetEntity.class);
		
		TweetEntity entity = repository.save(tweetEntity);
		
		tweetDto.setId(entity.getId());
		
		streamBridge.send(topic, entity);
		
		return tweetDto;
		
	}
}
