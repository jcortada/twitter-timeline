package com.atc.portal.turisme.model.dto;

import lombok.Data;

@Data
public class DocumentsAportatsDto {
    private Object cartaPagament;
    private DeclaracioFirmadaDto declaracioFirmada;
    private Object evidenciaCatcert;
    private Object peticioNetplus;
    private Object presentacio;
    private Object respostaNetplus;
    private RespostaTramitacioDto respostaTramitacio;
    private Object signaturaCatcertCartaPagament;
}
