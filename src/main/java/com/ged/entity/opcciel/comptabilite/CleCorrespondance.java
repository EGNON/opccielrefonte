package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleCorrespondance implements Serializable {
    @Column(length = 16)
    private String numeroCompteComptable;
    private String codePlan;
    private String codeIB;
    @Column(length = 16)
    private String codeRubrique;
    @Column(length = 16)
    private String codePosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleCorrespondance that)) return false;
        return Objects.equals(getNumeroCompteComptable(), that.getNumeroCompteComptable()) && Objects.equals(getCodePlan(), that.getCodePlan()) && Objects.equals(getCodeIB(), that.getCodeIB()) && Objects.equals(getCodeRubrique(), that.getCodeRubrique()) && Objects.equals(getCodePosition(), that.getCodePosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumeroCompteComptable(), getCodePlan(), getCodeIB(), getCodeRubrique(), getCodePosition());
    }

    public String getNumeroCompteComptable() {
        return numeroCompteComptable;
    }

    public void setNumeroCompteComptable(String numeroCompteComptable) {
        this.numeroCompteComptable = numeroCompteComptable;
    }

    public String getCodePlan() {
        return codePlan;
    }

    public void setCodePlan(String codePlan) {
        this.codePlan = codePlan;
    }

    public String getCodeIB() {
        return codeIB;
    }

    public void setCodeIB(String codeIB) {
        this.codeIB = codeIB;
    }

    public String getCodeRubrique() {
        return codeRubrique;
    }

    public void setCodeRubrique(String codeRubrique) {
        this.codeRubrique = codeRubrique;
    }

    public String getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(String codePosition) {
        this.codePosition = codePosition;
    }
}
