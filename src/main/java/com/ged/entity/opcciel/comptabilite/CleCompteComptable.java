package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleCompteComptable implements Serializable {
    private String numCompteComptable;
    private String codePlan;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleCompteComptable that = (CleCompteComptable) o;
        return Objects.equals(numCompteComptable, that.numCompteComptable) && Objects.equals(codePlan, that.codePlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCompteComptable, codePlan);
    }

    public CleCompteComptable() {
    }

    public String getNumCompteComptable() {
        return numCompteComptable;
    }

    public void setNumCompteComptable(String numCompteComptable) {
        this.numCompteComptable = numCompteComptable;
    }

    public String getCodePlan() {
        return codePlan;
    }

    public void setCodePlan(String codePlan) {
        this.codePlan = codePlan;
    }
}
