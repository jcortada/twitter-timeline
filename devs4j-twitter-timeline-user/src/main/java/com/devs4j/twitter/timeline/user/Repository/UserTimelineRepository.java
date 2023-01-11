package com.devs4j.twitter.timeline.user.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.twitter.timeline.user.model.entity.UserTimelineEntity;


@Repository
public interface UserTimelineRepository extends MongoRepository<UserTimelineEntity, String> {

}
