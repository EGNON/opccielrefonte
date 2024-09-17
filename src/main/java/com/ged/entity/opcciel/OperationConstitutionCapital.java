package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

import java.time.LocalDateTime;

/*@Entity
@Table(name = "T_OperationConstitutionCapital", schema = "Operation")*/
public class OperationConstitutionCapital extends Operation {
    private Double part;
    private Double valeurLiquidative;
    private boolean estGenere;
    private boolean estVerifie;
    private LocalDateTime dateVerification;
    private String userLoginVerificateur;

    public OperationConstitutionCapital() {
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
