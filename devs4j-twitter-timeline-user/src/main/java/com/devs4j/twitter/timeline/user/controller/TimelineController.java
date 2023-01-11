package com.devs4j.twitter.timeline.user.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.twitter.timeline.user.Repository.UserTimelineRepository;
import com.devs4j.twitter.timeline.user.model.dto.TweetDto;
import com.devs4j.twitter.timeline.user.model.entity.UserTimelineEntity;

@RestController
@RequestMapping("/timeline")
public class TimelineController {

	@Autowired
	UserTimelineRepository timelineRepository;

	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value = "/{accountId}")
	public ResponseEntity<List<TweetDto>> get(@PathVariable String accountId) {
		
		Optional<UserTimelineEntity> entity = timelineRepository.findById(accountId);

		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get().getTweets().stream()
					.map(x -> mapper.map(x, TweetDto.class))
					.toList());
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
				
	}
}
