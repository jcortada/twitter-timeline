package com.atc.portal.turisme;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.atc.portal.turisme.model.dto.DeclaracioDto;
import com.atc.portal.turisme.model.dto.DocumentDto;
import com.atc.portal.turisme.model.entity.FileStoreEntity;
import com.atc.portal.turisme.model.entity.LiquidationEntity;
import com.atc.portal.turisme.model.entity.SelfAssessmentDocumentEntity;
import com.atc.portal.turisme.model.entity.SelfAssessmentEntity;
import com.atc.portal.turisme.model.entity.TramitacioEntity;
import com.atc.portal.turisme.repository.FileStoreRepo;
import com.atc.portal.turisme.repository.LiquidationRepo;
import com.atc.portal.turisme.repository.PresentationRequestRepo;
import com.atc.portal.turisme.repository.SelfAssessmentRepo;
import com.atc.portal.turisme.services.DocumentService;
import com.atc.portal.turisme.services.ResultService;
import com.atc.portal.turisme.services.TramitacioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableFeignClients
@SpringBootApplication
public class ExportTurismeApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ExportTurismeApplication.class);

	@Value("${spring.profiles.active}")
	String profile;

	@Autowired
	PresentationRequestRepo presentationRequestRepo;

	@Autowired
	SelfAssessmentRepo selfAssessmentRepo;

	@Autowired
	FileStoreRepo fileStoreRepo;

	@Autowired
	LiquidationRepo liquidationRepo;

	@Autowired
	DocumentService documentService;

	@Autowired
	ResultService resultService;

	@Autowired
	TramitacioService tramitacioService;

	@Value("${presenters}")
	String presenters;

	public static void main(String[] args) {
		SpringApplication.run(ExportTurismeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// PresentationRequest entity = presentationRequestRepo.findById((long)
		// 3407876).orElseThrow();

		log.info("active profile: {}", profile);

		// doTaskTurisme();
		doTaskTramitacio();
	}

	private void doTaskTramitacio() {

		Collection<FileStoreEntity> list = fileStoreRepo
				.findAllFileStoreEntityPending(FileStoreEntity.docTypeEnum.JSON.name());

		log.info("total: {}", list.size());

		/*
		 * log.info("fecha de carga: {}, model: {}, taxyear: {}, amount {}",
		 * selfAssessment.getLoadDate(), selfAssessment.getModel(),
		 * selfAssessment.getTaxYear(), selfAssessment.getAmount());
		 * log.info("documentos: {}", selfAssessment.getDocuments().size());
		 * 
		 * getDocument(selfAssessment.getDocuments().get(0).getDocReference());
		 */
		for (FileStoreEntity item : list) {

			String docContent = item.getDocContent();
			byte[] decodedBytes = Base64.getDecoder().decode(docContent);
			String json = new String(decodedBytes);

			ObjectMapper objectMapper = new ObjectMapper();

			// Deserialization into the `Employee` class
			try {
				DeclaracioDto declaracio = objectMapper.readValue(json, DeclaracioDto.class);
			
				TramitacioEntity tramitacioEntity = tramitacioService.save(item.getReferencedId(), declaracio);

				log.info("idTramitacio: {} Justificant: {}", item.getReferencedId(), declaracio.getLiquidacio().getNumjustificant());
				
				List<LiquidationEntity> liquidations = liquidationRepo.findByLiquidationNumberEECC(declaracio.getLiquidacio().getNumjustificant());
				if(liquidations.size() == 0) 
					liquidations = liquidationRepo.findByLiquidationNumber(declaracio.getLiquidacio().getNumjustificant());
				
				if(liquidations.size() > 0) {
					log.info("LiquidationHistoricID: {} ", liquidations.get(0).getLiquidationHistoricID());
					if(tramitacioEntity != null) {
						tramitacioService.update(tramitacioEntity.getId(), liquidations.get(0));
					}
				}


			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

		}

	}

	private void doTaskTurisme() {

		String[] targets = presenters.split(",");

		for (String governementId : targets) {

			Collection<SelfAssessmentEntity> list = selfAssessmentRepo.findAllSelfAssessmentEntityError(governementId);

			log.info("total: {}", list.size());

			/*
			 * log.info("fecha de carga: {}, model: {}, taxyear: {}, amount {}",
			 * selfAssessment.getLoadDate(), selfAssessment.getModel(),
			 * selfAssessment.getTaxYear(), selfAssessment.getAmount());
			 * log.info("documentos: {}", selfAssessment.getDocuments().size());
			 * 
			 * getDocument(selfAssessment.getDocuments().get(0).getDocReference());
			 */
			for (SelfAssessmentEntity item : list) {
				Optional<SelfAssessmentDocumentEntity> filteredDocuments = item.getDocuments().stream()
						.filter(x -> x.getDocumentTypeID() == 9).findFirst();

				if (filteredDocuments.isPresent()) {
					DocumentDto documentDto = documentService
							.initializeDocument(filteredDocuments.get().getDocReference(), item);

					log.info("Document values: {}, {}, {}", documentDto.getExercici(), documentDto.getPeriode(),
							documentDto.getAmount());
					log.info("Portal values: {}, {}, {}", item.getTaxYear(), item.getPeriode(), item.getAmount());

					resultService.save(item, documentDto, governementId);
				}

			}
		}

	}

}
