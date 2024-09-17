package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationRegulEcartSoldeDto extends OperationDto {
    private Double SoldeEspeceDepositaire;

    public OperationRegulEcartSoldeDto() {
    }

    public Double getSoldeEspeceDepositaire() {
        return SoldeEspeceDepositaire;
    }

    public void setSoldeEspeceDepositaire(Double soldeEspeceDepositaire) {
        SoldeEspeceDepositaire = soldeEspeceDepositaire;
    }
}
