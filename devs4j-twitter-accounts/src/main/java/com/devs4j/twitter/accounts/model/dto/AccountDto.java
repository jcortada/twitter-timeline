package com.devs4j.twitter.accounts.model.dto;

import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	private String id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
	private int followersCount;
	private int followingCount;

	@Builder.Default()
	private List<String> followers = Collections.emptyList(); 

	@Builder.Default()
	private List<String> following = Collections.emptyList();
	
	public boolean isCelebrity() {
		return this.followersCount > 10;
	};

}
