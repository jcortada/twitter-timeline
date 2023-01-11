package com.devs4j.twitter.timeline.user.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.twitter.timeline.user.Repository.UserTimelineRepository;
import com.devs4j.twitter.timeline.user.model.dto.TweetDto;
import com.devs4j.twitter.timeline.user.model.dto.UserTimelineDto;
import com.devs4j.twitter.timeline.user.model.entity.UserTimelineEntity;
import com.devs4j.twitter.timeline.user.service.TimelineService;

@RestController
@RequestMapping("/timeline")
public class TimelineController {

	
	private static final Logger log = LoggerFactory.getLogger(TimelineController.class);

	
	@Autowired
	UserTimelineRepository timelineRepository;
	
	@Autowired
	TimelineService timelineService; 

	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value = "/{accountId}")
	public ResponseEntity<List<TweetDto>> get(@PathVariable String accountId) {
		
		Optional<UserTimelineDto> entity = timelineService.findById(accountId);
		
		if(entity.isPresent()) {
			/*
			 * for (String element : entity.get().getTweets()) { ResponseEntity<TweetDto>
			 * tweet = timelineService.getTweet(element); log.info("tweet: " +
			 * tweet.getBody()); }
			 */			
			return ResponseEntity.ok(entity.get().getTweets().stream()
				.map(x -> timelineService.getTweet(x).getBody())
				.toList());
			
		
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	
	}
}
