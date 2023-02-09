package com.atc.portal.turisme.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LiquidacioDto {
    private String dataPagament;
    private String idCept;
    @JsonProperty("import")
    private String amount;
    private String nif;
    private String nomComplet;
    private String nrc;
    private String numjustificant;

}
