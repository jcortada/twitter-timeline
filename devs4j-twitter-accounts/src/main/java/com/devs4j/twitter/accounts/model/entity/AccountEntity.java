package com.devs4j.twitter.accounts.model.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "accounts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
	//@DBRef
	private List<String> accountIds; 
}
