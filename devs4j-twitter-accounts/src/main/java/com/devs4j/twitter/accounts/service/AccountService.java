package com.devs4j.twitter.accounts.service;

import java.util.Optional;

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

	public Optional<AccountDto> findById(String accountId) {
	
		Optional<AccountEntity> entity = repository.findById(accountId);
		
		if(entity.isPresent()) {
			return Optional.of(mapper.map(entity.get(), AccountDto.class));
		}else {
			return Optional.empty();
		}
		
	}

	public void deleteAll() {
		repository.deleteAll();
		
	}

	public boolean follow(String accountId, String followId) {
		Optional<AccountEntity> account = repository.findById(accountId);
		Optional<AccountEntity> follow = repository.findById(followId);
		
		if (account.isPresent() && follow.isPresent()) {
			AccountEntity accountEntity = account.get();
			accountEntity.getFollowing().add(followId);
			accountEntity.setFollowingCount(accountEntity.getFollowingCount() + 1);
			
			repository.save(accountEntity);

			AccountEntity followEntity = follow.get();
			followEntity.getFollowers().add(accountId);
			followEntity.setFollowersCount(followEntity.getFollowersCount() + 1);

			repository.save(followEntity);

			return true;

		}
		
		return false;
	}
}
