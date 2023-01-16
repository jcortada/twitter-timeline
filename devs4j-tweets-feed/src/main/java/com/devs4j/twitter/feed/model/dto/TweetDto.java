package com.devs4j.twitter.feed.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetDto {

	private String id;
	private String text;
	private Date createdAt;

	private String owner;
}
