package com.atc.portal.turisme.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Data;

@Data
public class LiquidationDto {
	private long liquidationHistoricID;
	
	private int state;	
	private String liquidationNumber;	
	private String liquidationNumberEECC;	

	@Field(targetType = FieldType.DOUBLE)
	private BigDecimal amount;
	private Date registerDate;	
	private String userSignedDataGTRef;	
	private String idDeclaracio;
	private String idTramitacio;

}
