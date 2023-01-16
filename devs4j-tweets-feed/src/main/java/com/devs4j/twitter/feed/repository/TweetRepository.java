package com.devs4j.twitter.feed.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devs4j.twitter.feed.model.entity.TweetEntity;

public interface TweetRepository extends MongoRepository<TweetEntity, String>{

}
