package com.atc.portal.turisme.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name = "Common$SelfAssessmentDocument")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelfAssessmentDocumentEntity {

	@Id
	private long selfAssessmentDocumentID;
	private String docReference;
	private int documentTypeID;
	
	@ManyToOne
	@JoinColumn(name="SelfAssessmentID", nullable=false)
	private SelfAssessmentEntity selfAssessmentEntity;
}
