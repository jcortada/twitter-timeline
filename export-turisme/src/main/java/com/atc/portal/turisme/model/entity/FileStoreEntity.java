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
@Table(name = "Common$FileStore")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileStoreEntity {
	
	public enum docTypeEnum {
		PDF,
		JSON
	}
	
	@Id
	long DocumentID;
	String DocType;
	String Attributes;
	Date Date;
	String ReferencedId;
	int State;
	String DocContent;
}
