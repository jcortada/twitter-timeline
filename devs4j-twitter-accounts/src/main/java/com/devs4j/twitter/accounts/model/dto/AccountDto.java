package com.devs4j.twitter.accounts.model.dto;

import java.util.List;

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
	
	private List<String> accountIds; 
}
