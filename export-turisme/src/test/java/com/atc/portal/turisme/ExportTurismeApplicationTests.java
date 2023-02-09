package com.atc.portal.turisme;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.atc.portal.turisme.model.dto.DocumentDto;
import com.atc.portal.turisme.model.entity.SelfAssessmentEntity;
import com.atc.portal.turisme.services.DocumentService;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
class ExportTurismeApplicationTests {

	@MockBean
	DocumentService documentService; 
	
	@Mock
	SelfAssessmentEntity assessmentEntity;
	
	//@Test
	void contextLoads() {
	}
	
	
	@Test
	void testDocuments() {
		DocumentDto initializeDocument = documentService.initializeDocument("999999999", assessmentEntity);
		
		verify(documentService).initializeDocument("999999999", assessmentEntity);
		
	}

}
