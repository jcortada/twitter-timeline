package com.devs4j.twitter.timeline.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devs4j.twitter.timeline.user.model.dto.TweetDto;


@FeignClient("tweets-feed")
public interface TweetClient {

	@RequestMapping(method = RequestMethod.GET, value = "/feed/{tweetId}", produces = "application/json")
	TweetDto getTweet(@PathVariable String tweetId); 
}
