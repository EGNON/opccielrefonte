package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_OperationConstitutionCapital", schema = "Operation")
public class OperationConstitutionCapital extends Operation {
    @Column(precision = 18, scale = 6)
    private BigDecimal part;
    @Column(precision = 18, scale = 6)
    private BigDecimal valeurLiquidative;
    private Boolean estGenere;
    private Boolean estVerifie;
    private LocalDateTime dateVerification;
    private String userLoginVerificateur;

    public OperationConstitutionCapital() {
    }

    public BigDecimal getPart() {
        return part;
    }

    public void setPart(BigDecimal part) {
        this.part = part;
    }

    public BigDecimal getValeurLiquidative() {
        return valeurLiquidative;
    }

    public void setValeurLiquidative(BigDecimal valeurLiquidative) {
        this.valeurLiquidative = valeurLiquidative;
    }

    public Boolean getEstGenere() {
        return estGenere;
    }

    public void setEstGenere(Boolean estGenere) {
        this.estGenere = estGenere;
    }

    public Boolean getEstVerifie() {
        return estVerifie;
    }

    public void setEstVerifie(Boolean estVerifie) {
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
