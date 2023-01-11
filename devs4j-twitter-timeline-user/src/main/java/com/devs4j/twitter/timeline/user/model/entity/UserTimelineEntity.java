package com.devs4j.twitter.timeline.user.model.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "user_timeline")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTimelineEntity {

	/*
	 * AccountId
	 */
	@Id
	private String id;
	
	private List<String> tweets;
	private Date updatedAt;
}
