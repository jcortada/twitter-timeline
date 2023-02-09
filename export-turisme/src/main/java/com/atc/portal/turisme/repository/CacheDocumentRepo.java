package com.atc.portal.turisme.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atc.portal.turisme.model.entity.CacheDocumentEntity;

public interface CacheDocumentRepo extends MongoRepository<CacheDocumentEntity, String>{
	List<CacheDocumentEntity> findByDocReference(String docReference);

}
