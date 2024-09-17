package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationPaiementDATDto extends OperationDto {
    private Double montantRemb;
    private Double interet;
    private OperationDetachementDATDto operationDetachementDATDto;
    private Double irc;
    private String codeBanqueA;
    private String numCompteA;
    private Double interetMoratoire;

    public OperationPaiementDATDto() {
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

    public OperationDetachementDATDto getOperationDetachementDAT() {
        return operationDetachementDATDto;
    }

    public void setOperationDetachementDAT(OperationDetachementDATDto operationDetachementDATDto) {
        this.operationDetachementDATDto = operationDetachementDATDto;
    }

    public Double getIrc() {
        return irc;
    }

    public void setIrc(Double irc) {
        this.irc = irc;
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

    public Double getInteretMoratoire() {
        return interetMoratoire;
    }

    public void setInteretMoratoire(Double interetMoratoire) {
        this.interetMoratoire = interetMoratoire;
    }
}
