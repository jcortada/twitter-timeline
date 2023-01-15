package com.devs4j.twitter.timeline.user.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTimelineDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2562942982890352193L;
	
	/*
	 * AccountId
	 */
	private String id;
	private List<String> tweets;
	private Date updatedAt;
}
