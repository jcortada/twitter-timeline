package com.devs4j.twitter.timeline.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.security.auth.login.AccountNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.devs4j.twitter.timeline.user.Repository.UserTimelineRepository;
import com.devs4j.twitter.timeline.user.client.TweetClient;
import com.devs4j.twitter.timeline.user.model.dto.TweetDto;
import com.devs4j.twitter.timeline.user.model.dto.UserTimelineDto;
import com.devs4j.twitter.timeline.user.model.entity.UserTimelineEntity;

@Service
public class TimelineServiceImp implements TimelineService {

	private static final Logger log = LoggerFactory.getLogger(TimelineServiceImp.class);

	public Map<String,String> words=new HashMap<>();
	
	@PostConstruct
	public void init() {
		words.put("Hello", "Hola");
		words.put("Bye", "Adios");
		words.put("Word", "Palabra");
	}
	
	@Autowired
	UserTimelineRepository timelineRepository;

	@Autowired
	private TweetClient tweetClient;

	@Autowired
	ModelMapper mapper;

	@Bean
	private Consumer<Message<TweetDto>> TweetConsumer() {
		return msg -> {
			log.info(msg.toString());

			TweetDto tweetDto = msg.getPayload();


			try {
				addTweet(tweetDto.getOwner(), tweetDto);
			} catch (AccountNotFoundException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}

		};
	}
	

	@CachePut(value="translations", key="#accountId")
	public UserTimelineDto addTweet(String accountId, TweetDto tweetDto) throws AccountNotFoundException {
		
		UserTimelineEntity entity = findById(accountId);
		entity.getTweets().add(tweetDto.getId());
		entity.setUpdatedAt(new Date());
		
		entity = timelineRepository.save(entity);
		
		return mapper.map(entity, UserTimelineDto.class);		
	}

	@Override
	public UserTimelineDto save(UserTimelineDto timelineDto) {

		UserTimelineEntity entity = mapper.map(timelineDto, UserTimelineEntity.class);
		entity.setUpdatedAt(new Date());
		UserTimelineEntity save = timelineRepository.save(entity);
		timelineDto.setId(save.getId());
		return timelineDto;
	}


	public UserTimelineEntity findOrCreate(String accountId) {

		return timelineRepository.findById(accountId).orElseGet(() -> {
			return timelineRepository.save(UserTimelineEntity.builder()
					.id(accountId)
					.build());
		});

	}

	@Override
	//@Cacheable(key = "#message", value = "translations")
	public UserTimelineEntity findById(String message) throws AccountNotFoundException {
	
		return timelineRepository.findById(message).orElseThrow(() -> new AccountNotFoundException("Account not found"));

	}

	@Override
	@Cacheable(key = "#message", value = "translations")
	public Optional<String> findById2(String message) {
		log.info("findById2 {}",message);
	
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
		}
		return Optional.of("bye..."); 

	}

	@Override
	@Cacheable(key = "#message", value = "translations")
	public UserTimelineDto get(String message) throws AccountNotFoundException {
		log.info("Timeline entity result: {}", message);

		UserTimelineEntity entity = findById(message);
		//Optional<String> findById2 = findById2(accountId);
		
		
		return mapper.map(entity, UserTimelineDto.class);
	}
	
	@Override
	public ResponseEntity<TweetDto> getTweet(String tweetId) {
		TweetDto tweetDto = tweetClient.getTweet(tweetId);

		return ResponseEntity.ok(tweetDto);
	}

	@Override
	@Cacheable(key = "#message", value = "translations")
	public Optional<String> getTranslation(String message) {
		log.info("Doing translation for {}",message);
		for (String word : words.keySet()) {
			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
			}
			if(word.equals(message)) {
				return Optional.of(words.get(message));
			}
		}
		return Optional.empty();
	}
	/*
	 * public ResponseEntity<TweetDto> getTweet2(String tweetId) {
	 * 
	 * log.info("tweetId: " + tweetId); ResponseEntity<TweetDto> exchange =
	 * this.restTemplate.exchange( "http://tweets-feed/feed/{tweetId}",
	 * HttpMethod.GET, null, new ParameterizedTypeReference<TweetDto>() {},
	 * tweetId);
	 * 
	 * log.info("tweet: " + exchange.getBody());
	 * 
	 * return exchange; }
	 */
}
