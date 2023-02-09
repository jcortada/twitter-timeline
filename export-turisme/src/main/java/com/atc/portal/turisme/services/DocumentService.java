package com.atc.portal.turisme.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.atc.portal.turisme.clients.GestorDocumentalClient;
import com.atc.portal.turisme.model.dto.DocumentDto;
import com.atc.portal.turisme.model.dto.GestorDocumentalResponseDto;
import com.atc.portal.turisme.model.entity.CacheDocumentEntity;
import com.atc.portal.turisme.model.entity.SelfAssessmentEntity;
import com.atc.portal.turisme.repository.CacheDocumentRepo;

@Service
public class DocumentService {
	
	@Value("${osb.username}")
	String username;

	@Value("${osb.password}")
	String password;

	
	private static final Logger log = LoggerFactory.getLogger(DocumentService.class);


	@Autowired
	private GestorDocumentalClient client;
	
	@Autowired
	CacheDocumentService cacheDocumentService;
	
	public DocumentDto initializeDocument(String docReference, SelfAssessmentEntity item) {
		String content = null;
		
		log.info("Processing: {}", docReference);
		
		List<CacheDocumentEntity> list = getCacheDocument(docReference);
		
		if(list.size() == 0) {
			GestorDocumentalResponseDto response = getDocument(docReference);
			content = response.getDocument();
			
			cacheDocumentService.save(docReference, item.getSelfAssessmentNumber(),  content);

		} else {
			content = list.get(0).getContent();
		}
		
		
		return new DocumentDto(docReference, content);
	}
	
	private GestorDocumentalResponseDto getDocument(String docReference) {

		byte[] encodedBytes = Base64Utils.encode((username + ":" + password).getBytes());

		String authHeader = "Basic " + new String(encodedBytes);

		return client.descarregarDocument(authHeader, "PORTAL", docReference, "PORTAL");
	}


	private List<CacheDocumentEntity> getCacheDocument(String docReference) {
		 return cacheDocumentService.findByDocReference(docReference);
				
	}

}
