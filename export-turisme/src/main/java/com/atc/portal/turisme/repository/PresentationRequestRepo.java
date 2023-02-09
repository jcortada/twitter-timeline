package com.atc.portal.turisme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atc.portal.turisme.model.entity.PresentationRequestEntity;

public interface PresentationRequestRepo extends JpaRepository<PresentationRequestEntity, Long> {

}
