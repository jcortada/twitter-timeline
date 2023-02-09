package com.atc.portal.turisme.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.portal.turisme.model.dto.DocumentDto;
import com.atc.portal.turisme.model.entity.ResultEntity;
import com.atc.portal.turisme.model.entity.SelfAssessmentEntity;
import com.atc.portal.turisme.repository.ResultRepo;

@Service
public class ResultService {

	@Autowired
	ResultRepo resultRepo;

	public ResultEntity save(SelfAssessmentEntity selfAssessmentEntity, DocumentDto documentDto, String governementId) {

		Optional<ResultEntity> item = resultRepo.findByselfAssessmentNumber(selfAssessmentEntity.getSelfAssessmentNumber());

		if (!item.isPresent()) {

			ResultEntity resultEntity = ResultEntity.builder()
					.selfAssessmentNumber(selfAssessmentEntity.getSelfAssessmentNumber())
					.exercici(String.valueOf(selfAssessmentEntity.getTaxYear()))
					.periode(selfAssessmentEntity.getPeriode())
					.presenterXml(documentDto.getPresenter())
					.presenter(governementId)
					.loadDate(selfAssessmentEntity.getLoadDate())
					.amount(
							selfAssessmentEntity.getAmount().setScale(2, RoundingMode.HALF_UP).stripTrailingZeros())
					.exerciciXml(documentDto.getExercici()).periodeXml(documentDto.getPeriode())
					.amountXml(
							documentDto.getAmount() != null? documentDto.getAmount().setScale(2, RoundingMode.HALF_UP).stripTrailingZeros() : new BigDecimal(0))
					.build();

			return resultRepo.save(resultEntity);
		}
		
		return item.get();

	}
}
