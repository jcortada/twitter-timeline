package com.devs4j.twitter.timeline.user.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9079133105918336695L;

	public AccountNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


	
}
