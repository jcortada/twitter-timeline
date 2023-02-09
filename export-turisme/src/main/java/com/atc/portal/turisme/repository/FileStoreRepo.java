package com.atc.portal.turisme.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atc.portal.turisme.model.entity.FileStoreEntity;

public interface FileStoreRepo extends JpaRepository<FileStoreEntity, Long> {

	@Query(value = "select *"
			+ "  FROM [PortalTributario].[dbo].[Common$FileStore]\r\n"
			+ "  where Date > '2023-02-02'\r\n"
			+ "  and State = 1 \r\n"
			+ "  and DocType = :docType", nativeQuery = true)
	Collection<FileStoreEntity> findAllFileStoreEntityPending(@Param("docType")String docType);
}
