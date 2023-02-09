package com.atc.portal.turisme.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "Result")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity {
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String selfAssessmentNumber;
	private String periode;
	private String exercici;
	private String presenter;
	private Date loadDate;
	
	@Field(targetType = FieldType.DOUBLE)
	private BigDecimal amount;
	
	private String periodeXml;
	private String exerciciXml;
	private String presenterXml;
	
	@Field(targetType = FieldType.DOUBLE)
	private BigDecimal amountXml;
}
