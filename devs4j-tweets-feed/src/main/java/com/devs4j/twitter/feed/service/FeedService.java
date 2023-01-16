package com.devs4j.twitter.feed.service;

import java.util.Optional;
import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devs4j.twitter.feed.model.dto.TweetDto;
import com.devs4j.twitter.feed.model.entity.TweetEntity;
import com.devs4j.twitter.feed.repository.TweetRepository;

@Service
public class FeedService {
	
	
	private static final Logger log = LoggerFactory.getLogger(FeedService.class);

	
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

	public Optional<TweetDto> findById(String tweetId) {
		log.info("find by id: " + tweetId);
		
		Optional<TweetEntity> entity = repository.findById(tweetId);
		
		if(entity.isPresent()) {
			return Optional.of(mapper.map(entity.get(), TweetDto.class));
			
		} else {
			return Optional.empty();
		}


	}
}
