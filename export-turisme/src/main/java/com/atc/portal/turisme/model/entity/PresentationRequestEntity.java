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
@Table(name = "SAM$PresentationRequest")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresentationRequestEntity {
	@Id
	private Long presentationRequestID;
	private long comunicationID;
	private long requestOriginID;
	private String cultureName;
	private long currentPresentationStateID;
	private long lastStablePresentationStateID;
	private Date receptionDate;
}
