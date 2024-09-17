package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationRuptureAnticipeDATDto extends OperationDto {
    private Double montantRemb;
    private Double interet;
    private Double interetRupture;
    private String codeBanqueA;
    private String numCompteA;

    public OperationRuptureAnticipeDATDto() {
    }

    public Double getMontantRemb() {
        return montantRemb;
    }

    public void setMontantRemb(Double montantRemb) {
        this.montantRemb = montantRemb;
    }

    public Double getInteret() {
        return interet;
    }

    public void setInteret(Double interet) {
        this.interet = interet;
    }

    public Double getInteretRupture() {
        return interetRupture;
    }

    public void setInteretRupture(Double interetRupture) {
        this.interetRupture = interetRupture;
    }

    public String getCodeBanqueA() {
        return codeBanqueA;
    }

    public void setCodeBanqueA(String codeBanqueA) {
        this.codeBanqueA = codeBanqueA;
    }

    public String getNumCompteA() {
        return numCompteA;
    }

    public void setNumCompteA(String numCompteA) {
        this.numCompteA = numCompteA;
    }
}
