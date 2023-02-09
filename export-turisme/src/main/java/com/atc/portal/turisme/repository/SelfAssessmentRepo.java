package com.atc.portal.turisme.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atc.portal.turisme.model.entity.SelfAssessmentEntity;


public interface SelfAssessmentRepo extends JpaRepository<SelfAssessmentEntity, Long>{
 
	List<SelfAssessmentEntity> findBySelfAssessmentNumber(String number);
	
	@Query(value = "SELECT Ass.*\r\n"
			+ "FROM [PortalTributario].[dbo].[Common$SelfAssessment] Ass\r\n"
			+ "INNER JOIN [PortalTributario].[dbo].[SAM$PresentationRequest] Req\r\n"
			+ "ON Ass.PresentationRequestID = Req.PresentationRequestID\r\n"
			+ "INNER JOIN [PortalTributario].[dbo].[SAM$PresentationState] Sta\r\n"
			+ "ON Req.CurrentPresentationStateID = Sta.PresentationStateID\r\n"
			+ "--LEFT JOIN Payment$PaymentRequest pr on pr.TreatedID = Ass.SelfAssessmentID\r\n"
			+ "--LEFT JOIN Payment$PaymentStatus ps on ps.PaymentStatusID = pr.PaymentStatusID\r\n"
			+ "--LEFT JOIN Common$AssessmentPayment ap on ap.AssessmentPaymentID = Ass.AssessmentPaymentID\r\n"
			+ "LEFT JOIN Common$Actor ac on ac.SelfAssessmentID = ass.SelfAssessmentID\r\n"
			+ "LEFT JOIN Common$Person p on p.PersonID = ac.PersonID\r\n"
			+ "LEFT JOIN Common$Role ro on ro.RoleID = ac.RoleID \r\n"
			+ "WHERE Ass.Model IN('920', '940', '950')\r\n"
			+ "AND Ass.TaxYear in (2020) --(2018,2019,2020)--,2021,2022)\r\n"
			+ "AND Sta.PresentationStateID IN(8, 10, 14)\r\n"
			+ "--AND (ps.Name = 'Paid' or ps.Name is null)\r\n"
			+ "--AND ps.Name is null AND ap.NRC is not null\r\n"
			+ "AND ro.RoleName = 'PR'\r\n"
			+ "AND UPPER(p.GovernmentID) = :presenter", nativeQuery = true)
	Collection<SelfAssessmentEntity> findAllSelfAssessmentEntityError(@Param("presenter")String presenter);
}
