package com.devs4j.twitter.accounts.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.twitter.accounts.model.dto.AccountDto;
import com.devs4j.twitter.accounts.service.AccountService;

@RequestMapping("/account")
@RestController
public class AccountController {
	
	@Autowired
	AccountService service; 
	
	@GetMapping("/{accountId}")
	public ResponseEntity<AccountDto> get(@PathVariable String accountId) {
		Optional<AccountDto> result = service.findById(accountId);
		
		if(result.isPresent()) {
			return ResponseEntity.ok(result.get());
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{accountId}/follow/{followId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void follow(@PathVariable String accountId, @PathVariable String followId) {
		if(!service.follow(accountId, followId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
	}

}
