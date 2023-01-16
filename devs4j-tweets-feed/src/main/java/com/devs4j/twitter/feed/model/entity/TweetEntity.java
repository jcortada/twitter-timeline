package com.devs4j.twitter.feed.model.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "tweets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetEntity {

	@Id
	private String id;
	private String text;
	private Date createdAt;

	//@DBRef
	private String owner;
}
