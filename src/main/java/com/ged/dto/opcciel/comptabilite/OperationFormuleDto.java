package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationFormuleDto {
    @JsonIgnore
    private OperationDto operationDto;
    private FormuleDto formule;
    private Double valeur;

    public OperationFormuleDto() {
    }

    public OperationDto getOperationDto() {
        return operationDto;
    }

    public void setOperationDto(OperationDto operationDto) {
        this.operationDto = operationDto;
    }

    public FormuleDto getFormule() {
        return formule;
    }

    public void setFormule(FormuleDto formule) {
        this.formule = formule;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
