package com.devs4j.twitter.accounts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.twitter.accounts.model.entity.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, String>{

}
