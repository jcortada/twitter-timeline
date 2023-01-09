package com.devs4j.tweets.feed.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs4j.tweets.feed.model.dto.TweetDto;
import com.devs4j.tweets.feed.model.entity.TweetEntity;
import com.devs4j.tweets.feed.repository.TweetRepository;

@Service
public class FeedService {

	@Autowired
	private TweetRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public void save(TweetDto tweetDto) {
		TweetEntity tweetEntity = mapper.map(tweetDto, TweetEntity.class);
		
		repository.save(tweetEntity);
	}
}
