package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationConstitutionCapitalDto extends OperationDto {
    private Double part;
    private Double valeurLiquidative;
    private boolean estGenere;
    private boolean estVerifie;
    private LocalDateTime dateVerification;
    private String userLoginVerificateur;

    public OperationConstitutionCapitalDto() {
    }

    public Double getPart() {
        return part;
    }

    public void setPart(Double part) {
        this.part = part;
    }

    public Double getValeurLiquidative() {
        return valeurLiquidative;
    }

    public void setValeurLiquidative(Double valeurLiquidative) {
        this.valeurLiquidative = valeurLiquidative;
    }

    public boolean isEstGenere() {
        return estGenere;
    }

    public void setEstGenere(boolean estGenere) {
        this.estGenere = estGenere;
    }

    public boolean isEstVerifie() {
        return estVerifie;
    }

    public void setEstVerifie(boolean estVerifie) {
        this.estVerifie = estVerifie;
    }

    public LocalDateTime getDateVerification() {
        return dateVerification;
    }

    public void setDateVerification(LocalDateTime dateVerification) {
        this.dateVerification = dateVerification;
    }

    public String getUserLoginVerificateur() {
        return userLoginVerificateur;
    }

    public void setUserLoginVerificateur(String userLoginVerificateur) {
        this.userLoginVerificateur = userLoginVerificateur;
    }
}
