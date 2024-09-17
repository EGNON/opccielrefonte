package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClePosteComptable implements Serializable {
    @Column(length = 16)
    private String codePosteComptable;
    @Column(length = 16)
    private String codePlan;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClePosteComptable that = (ClePosteComptable) o;
        return Objects.equals(codePosteComptable, that.codePosteComptable) && Objects.equals(codePlan, that.codePlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codePosteComptable, codePlan);
    }

    public ClePosteComptable() {
    }

    public String getCodePosteComptable() {
        return codePosteComptable;
    }

    public void setCodePosteComptable(String codePosteComptable) {
        this.codePosteComptable = codePosteComptable;
    }

    public String getCodePlan() {
        return codePlan;
    }

    public void setCodePlan(String codePlan) {
        this.codePlan = codePlan;
    }
}
