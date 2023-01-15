package com.devs4j.twitter.timeline.user.service;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.ResponseEntity;

import com.devs4j.twitter.timeline.user.model.dto.TweetDto;
import com.devs4j.twitter.timeline.user.model.dto.UserTimelineDto;
import com.devs4j.twitter.timeline.user.model.entity.UserTimelineEntity;

public interface TimelineService {

	UserTimelineDto save(UserTimelineDto timelineDto);

	UserTimelineEntity findById(String id) throws AccountNotFoundException;

	ResponseEntity<TweetDto> getTweet(String tweetId);

	UserTimelineDto get(String accountId) throws AccountNotFoundException;

	Optional<String> getTranslation(String message);

	Optional<String> findById2(String accountId);

}