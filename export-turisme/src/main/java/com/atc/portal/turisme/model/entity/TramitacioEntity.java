package com.atc.portal.turisme.model.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.atc.portal.turisme.model.dto.DeclaracioDto;
import com.atc.portal.turisme.model.dto.LiquidationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "Tramitacio")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TramitacioEntity {
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String idTramitacio;
	
	private String numjustificant;
	private DeclaracioDto declaracio;
	
	private LiquidationDto liquidation;


}
