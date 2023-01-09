package com.devs4j.tweets.feed.repository;

import org.springframework.data.repository.CrudRepository;

import com.devs4j.tweets.feed.model.entity.TweetEntity;

public interface TweetRepository extends CrudRepository<TweetEntity, String>{

}
