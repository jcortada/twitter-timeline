package com.atc.portal.turisme.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atc.portal.turisme.model.entity.TramitacioEntity;

public interface TramitacioRepo extends MongoRepository<TramitacioEntity, String> {
	Optional<TramitacioEntity> findByidTramitacio(String idTramitacio);

}
