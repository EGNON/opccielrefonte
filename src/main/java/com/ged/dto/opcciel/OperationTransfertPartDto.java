package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.standard.PersonneDto;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationTransfertPartDto extends OperationDto {
    private PersonneDto demandeur;
    private PersonneDto beneficiaire;
    private BigDecimal cumpEntre;
    private BigDecimal qteInitiale;
    private BigDecimal qteTransfert;
    private Long idOpDepart;

    public OperationTransfertPartDto() {
    }

    public PersonneDto getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(PersonneDto demandeur) {
        this.demandeur = demandeur;
    }

    public PersonneDto getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(PersonneDto beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public BigDecimal getCumpEntre() {
        return cumpEntre;
    }

    public void setCumpEntre(BigDecimal cumpEntre) {
        this.cumpEntre = cumpEntre;
    }

    public BigDecimal getQteInitiale() {
        return qteInitiale;
    }

    public void setQteInitiale(BigDecimal qteInitiale) {
        this.qteInitiale = qteInitiale;
    }

    public BigDecimal getQteTransfert() {
        return qteTransfert;
    }

    public void setQteTransfert(BigDecimal qteTransfert) {
        this.qteTransfert = qteTransfert;
    }

    public Long getIdOpDepart() {
        return idOpDepart;
    }

    public void setIdOpDepart(Long idOpDepart) {
        this.idOpDepart = idOpDepart;
    }
}
