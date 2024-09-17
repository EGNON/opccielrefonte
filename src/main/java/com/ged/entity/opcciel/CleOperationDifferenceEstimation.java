package com.ged.entity.opcciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleOperationDifferenceEstimation implements Serializable {
    private long idSeance;
    private long idTitre;
    private long idOpcvm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleOperationDifferenceEstimation that = (CleOperationDifferenceEstimation) o;
        return idSeance == that.idSeance && idTitre == that.idTitre && idOpcvm == that.idOpcvm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSeance, idTitre, idOpcvm);
    }

    public CleOperationDifferenceEstimation() {
    }

    public long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(long idSeance) {
        this.idSeance = idSeance;
    }

    public long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(long idTitre) {
        this.idTitre = idTitre;
    }

    public long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }
}
