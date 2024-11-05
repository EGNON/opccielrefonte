package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClePosteComptableSeanceOpcvm implements Serializable {
    private String codePosteComptable;
    private String codePlan;
    private Long idOpcvm;
    private Long idSeance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClePosteComptableSeanceOpcvm that = (ClePosteComptableSeanceOpcvm) o;
        return Objects.equals(codePosteComptable, that.codePosteComptable) && Objects.equals(codePlan, that.codePlan) && Objects.equals(idOpcvm, that.idOpcvm) && Objects.equals(idSeance, that.idSeance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codePosteComptable, codePlan, idOpcvm, idSeance);
    }

    public ClePosteComptableSeanceOpcvm() {
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

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }
}
