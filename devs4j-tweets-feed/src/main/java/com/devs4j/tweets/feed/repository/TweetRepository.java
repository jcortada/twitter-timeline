package com.devs4j.tweets.feed.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devs4j.tweets.feed.model.entity.TweetEntity;

public interface TweetRepository extends MongoRepository<TweetEntity, String>{

}
