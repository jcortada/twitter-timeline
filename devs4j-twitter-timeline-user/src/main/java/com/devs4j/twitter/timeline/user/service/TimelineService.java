package com.devs4j.twitter.timeline.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.devs4j.twitter.timeline.user.Repository.UserTimelineRepository;
import com.devs4j.twitter.timeline.user.model.dto.TweetDto;
import com.devs4j.twitter.timeline.user.model.dto.UserTimelineDto;
import com.devs4j.twitter.timeline.user.model.entity.UserTimelineEntity;

@Component
public class TimelineService {

	private static final Logger log = LoggerFactory.getLogger(TimelineService.class);

	@Autowired
	UserTimelineRepository timelineRepository;

    @Autowired
    private RestTemplate restTemplate;
    
	@Autowired
	ModelMapper mapper;

	@Bean
	private Consumer<Message<TweetDto>> TweetConsumer() {
		return msg -> {
			log.info(msg.toString());
			
			TweetDto tweetDto = msg.getPayload();
			
			UserTimelineEntity entity = update(tweetDto.getOwner());
			
			entity.getTweets().add(tweetDto.getId());
			
			save(entity);
			
		};
	}

	public UserTimelineDto save(UserTimelineDto timelineDto) {

		UserTimelineEntity entity = mapper.map(timelineDto, UserTimelineEntity.class);
		UserTimelineEntity save = timelineRepository.save(entity);
		timelineDto.setId(save.getId());
		return timelineDto;
	}

	public void save(UserTimelineEntity entity) {
		entity.setUpdatedAt(new Date());

		UserTimelineEntity save = timelineRepository.save(entity);
	}

	public UserTimelineEntity update(String id) {

		return timelineRepository.findById(id).orElseGet(() -> {
			return timelineRepository.save(UserTimelineEntity
					.builder()
					.id(id)
					.tweets(new ArrayList<String>())
					.build());
		});
		
	}
	
	public Optional<UserTimelineDto> findById(String id) {

		Optional<UserTimelineEntity> entity = timelineRepository.findById(id);
		
		if(entity.isPresent()) {
			return Optional.of(mapper.map(entity.get(), UserTimelineDto.class));
		}else {
			return Optional.empty();
		}
	}
	
	
	public ResponseEntity<TweetDto>  getTweet(String tweetId) {
		
		log.info("tweetId: " + tweetId);
		ResponseEntity<TweetDto> exchange =
                this.restTemplate.exchange(
                        "http://tweets-feed/feed/{tweetId}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<TweetDto>() {},
                        tweetId);

		log.info("tweet: " + exchange.getBody());
		
		return exchange;
	}
}
