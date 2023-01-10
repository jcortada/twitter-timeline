package com.devs4j.tweets.feed.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.tweets.feed.model.dto.TweetDto;
import com.devs4j.tweets.feed.service.FeedService;

@RequestMapping("/feed")
@RestController
public class FeedController {

	
	private static final Logger log = LoggerFactory.getLogger(FeedController.class);

	
	@Autowired
	private FeedService feedService;

	@PostMapping
	public ResponseEntity<TweetDto> post(@RequestBody TweetDto tweetDto) {

		tweetDto.setCreatedAt(new Date());
		tweetDto = feedService.save(tweetDto);

		log.info("tweet received: {}", tweetDto);
		
		return ResponseEntity.ok(tweetDto);
	}
}
