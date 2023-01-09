package com.devs4j.twitter.accounts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.twitter.accounts.model.entity.AccountEntity;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, String>{

}
