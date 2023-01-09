package com.devs4j.twitter.accounts.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs4j.twitter.accounts.model.dto.AccountDto;
import com.devs4j.twitter.accounts.model.entity.AccountEntity;
import com.devs4j.twitter.accounts.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;
	
	
	@Autowired
	private ModelMapper mapper;
	
	public AccountDto save(AccountDto accountDto) {

		AccountEntity accountEntity = mapper.map(accountDto, AccountEntity.class);
		
		AccountEntity entity = repository.save(accountEntity);
		
		accountDto.setId(entity.getId());
		
		return accountDto;
	}

	public void deleteAll() {
		repository.deleteAll();
		
	}
}
