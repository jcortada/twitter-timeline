package com.atc.portal.turisme.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atc.portal.turisme.model.entity.ResultEntity;

public interface ResultRepo extends MongoRepository<ResultEntity, String> {
	Optional<ResultEntity> findByselfAssessmentNumber(String selfAssessmentNumber);
}
