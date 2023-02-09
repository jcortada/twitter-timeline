package com.atc.portal.turisme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.portal.turisme.model.entity.CacheDocumentEntity;
import com.atc.portal.turisme.repository.CacheDocumentRepo;

@Service
public class CacheDocumentService {

	@Autowired
	CacheDocumentRepo cacheDocumentRepo;
	
	
	public CacheDocumentEntity save(String docReference, String selfAssessment, String content) {
		
		CacheDocumentEntity documentEntity = CacheDocumentEntity.builder()
			.docReference(docReference)
			.selfassessmentNumber(selfAssessment)
			.content(content)
			.build();
		
		return cacheDocumentRepo.save(documentEntity);
	}
	
	public List<CacheDocumentEntity> findByDocReference(String docReference) {
		return cacheDocumentRepo.findByDocReference(docReference);
	}
}
