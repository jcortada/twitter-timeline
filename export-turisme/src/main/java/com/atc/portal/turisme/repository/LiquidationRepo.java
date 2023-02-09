package com.atc.portal.turisme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atc.portal.turisme.model.entity.LiquidationEntity;

public interface LiquidationRepo extends JpaRepository<LiquidationEntity, Long>{
	List<LiquidationEntity> findByLiquidationNumber(String liquidationNumber);
	List<LiquidationEntity> findByLiquidationNumberEECC(String liquidationNumberEECC);
}
