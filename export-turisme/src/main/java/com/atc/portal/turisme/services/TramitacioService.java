package com.atc.portal.turisme.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.portal.turisme.model.dto.DeclaracioDto;
import com.atc.portal.turisme.model.dto.LiquidationDto;
import com.atc.portal.turisme.model.entity.LiquidationEntity;
import com.atc.portal.turisme.model.entity.TramitacioEntity;
import com.atc.portal.turisme.repository.TramitacioRepo;

@Service
public class TramitacioService {
	@Autowired
	TramitacioRepo tramitacioRepo;
	
	@Autowired
	private ModelMapper mapper;

	public TramitacioEntity save(String idTramitacio, DeclaracioDto declaracio) {

		Optional<TramitacioEntity> item = tramitacioRepo.findByidTramitacio(idTramitacio);

		if (!item.isPresent()) {

			TramitacioEntity tramitacioEntity = TramitacioEntity.builder()
					.idTramitacio(idTramitacio)
					.numjustificant(declaracio.getLiquidacio().getNumjustificant())
					.declaracio(declaracio)
					.build();

			return tramitacioRepo.save(tramitacioEntity);
		}
		
		return item.get();

	}

	public TramitacioEntity update(String id, LiquidationEntity liquidationEntity) {
		Optional<TramitacioEntity> item = tramitacioRepo.findById(id);

		if (item.isPresent()) {
			TramitacioEntity tramitacioEntity = item.get();
			
			LiquidationDto liquidationDto = mapper.map(liquidationEntity, LiquidationDto.class);
			
			
			tramitacioEntity.setLiquidation(liquidationDto);
			return tramitacioRepo.save(tramitacioEntity);
		}		
		
		return null;
	}
}
