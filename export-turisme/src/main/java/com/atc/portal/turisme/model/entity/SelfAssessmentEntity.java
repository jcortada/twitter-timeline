package com.atc.portal.turisme.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name = "Common$SelfAssessment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelfAssessmentEntity {
	
	@Id
	private long selfAssessmentID;
	private String selfAssessmentNumber;
	private Date loadDate;
	private String model;
	private String taxConcept;
	private int taxYear;
	private String incomePeriod;
	private BigDecimal amount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PresentationRequestID", referencedColumnName = "PresentationRequestID")
	private PresentationRequestEntity presentationRequest;
	
	@OneToMany(mappedBy = "selfAssessmentEntity", fetch = FetchType.EAGER)
	private List<SelfAssessmentDocumentEntity> documents; 
		

	public String getPeriode() {
		return incomePeriod.substring(incomePeriod.length() - 2, incomePeriod.length());
	}
	
}
