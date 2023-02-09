package com.atc.portal.turisme.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atc.portal.turisme.model.dto.GestorDocumentalResponseDto;

@FeignClient(value = "GestorDocumental", url = "${osb.gestorDocumental.url}")
public interface GestorDocumentalClient {

	
	 @RequestMapping(method = RequestMethod.GET, value = "/descarregarDocument", consumes = "application/json")
	 GestorDocumentalResponseDto descarregarDocument(@RequestHeader("Authorization") String header,
			 @RequestParam(name = "aplicacio") String aplicacio,
			 @RequestParam(name = "id_document") String id_document,
			 @RequestParam(name = "usuari_nominal") String usuari_nominal);
}
