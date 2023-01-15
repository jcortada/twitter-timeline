package com.devs4j.twitter.timeline.user.model.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
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
public class UserTimelineEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5740527558627125042L;

	/*
	 * AccountId
	 */
	@Id
	private String id;
	
	@Builder.Default
	private List<String> tweets = Collections.emptyList();
	private Date updatedAt;
}
