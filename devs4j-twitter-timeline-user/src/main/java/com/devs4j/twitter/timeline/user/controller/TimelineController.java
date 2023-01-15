package com.devs4j.twitter.timeline.user.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.twitter.timeline.user.Repository.UserTimelineRepository;
import com.devs4j.twitter.timeline.user.model.dto.TweetDto;
import com.devs4j.twitter.timeline.user.model.dto.UserTimelineDto;
import com.devs4j.twitter.timeline.user.service.TimelineService;

@RestController
@RequestMapping("/user-timeline")
public class TimelineController {

	private static final Logger log = LoggerFactory.getLogger(TimelineController.class);

	@Autowired
	UserTimelineRepository timelineRepository;

	@Autowired
	TimelineService timelineService;

	@Autowired
	ModelMapper mapper;

	@GetMapping("/translations")
	public ResponseEntity<UserTimelineDto> get(@RequestParam("accountId") String accountId) throws AccountNotFoundException {
	//public ResponseEntity<String> get(@RequestParam("accountId") String accountId) throws AccountNotFoundException {

		//UserTimelineDto entity = timelineService.get(accountId);
		//timelineService.get(accountId);
		UserTimelineDto userTimelineDto = timelineService.get(accountId);

		return ResponseEntity.ok(userTimelineDto);
		
		/*
		entity.getTweets()
				.stream()
				.map(x -> timelineService.getTweet(x).getBody())
				(type[]) collection.toArray(new type[collection.size()]))*/
				

	}
	
	@GetMapping("/translations2")
	public ResponseEntity<String> getTranslation(@RequestParam("message") String message) {
		log.info("Message received {} ", message);
		Optional<String> translation = timelineService.getTranslation(message);
		if(translation.isPresent()) {
			return ResponseEntity.ok(translation.get());
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
