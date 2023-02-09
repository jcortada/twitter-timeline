package com.atc.portal.turisme.model.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "CacheDocuments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CacheDocumentEntity {
	@Id
	private String id;
	private String content;
	private String docReference;
	private String selfassessmentNumber;

}




