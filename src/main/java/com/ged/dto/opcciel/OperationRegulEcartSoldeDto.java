package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationRegulEcartSoldeDto extends OperationDto {
    private BigDecimal soldeEspeceDepositaire;
    private BigDecimal ecart;
    private String userLogin;

    public OperationRegulEcartSoldeDto() {
    }

    public BigDecimal getSoldeEspeceDepositaire() {
        return soldeEspeceDepositaire;
    }

    public void setSoldeEspeceDepositaire(BigDecimal soldeEspeceDepositaire) {
        this.soldeEspeceDepositaire = soldeEspeceDepositaire;
    }

    public BigDecimal getEcart() {
        return ecart;
    }

    public void setEcart(BigDecimal ecart) {
        this.ecart = ecart;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
