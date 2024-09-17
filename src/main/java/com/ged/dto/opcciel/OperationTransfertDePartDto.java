package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonneDto;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationTransfertDePartDto {
    private String idOperation;
    private PersonneDto personneDemandeur;
    private Double qteInitialeD;
    private PersonneDto personneBeneficiaire;
    private Double qteInitialeB;
    private Double cumpEntre;
    private Double qteTransfert;
    private LocalDateTime dateOperation;
    private OpcvmDto opcvm;

    public OperationTransfertDePartDto() {
    }

    public String getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(String idOperation) {
        this.idOperation = idOperation;
    }

    public PersonneDto getPersonneDemandeur() {
        return personneDemandeur;
    }

    public void setPersonneDemandeur(PersonneDto personneDemandeur) {
        this.personneDemandeur = personneDemandeur;
    }

    public Double getQteInitialeD() {
        return qteInitialeD;
    }

    public void setQteInitialeD(Double qteInitialeD) {
        this.qteInitialeD = qteInitialeD;
    }

    public PersonneDto getPersonneBeneficiaire() {
        return personneBeneficiaire;
    }

    public void setPersonneBeneficiaire(PersonneDto personneBeneficiaire) {
        this.personneBeneficiaire = personneBeneficiaire;
    }

    public Double getQteInitialeB() {
        return qteInitialeB;
    }

    public void setQteInitialeB(Double qteInitialeB) {
        this.qteInitialeB = qteInitialeB;
    }

    public Double getCumpEntre() {
        return cumpEntre;
    }

    public void setCumpEntre(Double cumpEntre) {
        this.cumpEntre = cumpEntre;
    }

    public Double getQteTransfert() {
        return qteTransfert;
    }

    public void setQteTransfert(Double qteTransfert) {
        this.qteTransfert = qteTransfert;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }
}
