package com.atc.portal.turisme.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name = "Common$LiquidationHistoric")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiquidationEntity {
	@Id
	private long liquidationHistoricID;
	
	private String state;	
	private String liquidationNumber;	
	private String liquidationNumberEECC;	
	private String amount;
	private Date registerDate;	
	private String userSignedDataGTRef;	
	private String idDeclaracio;
	private String idTramitacio;


}
