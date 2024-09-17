package com.ged.entity.opcciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleSeanceOpcvm implements Serializable {
    private Long idOpcvm;
    private Long idSeance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleSeanceOpcvm that = (CleSeanceOpcvm) o;
        return idOpcvm == that.idOpcvm && idSeance == that.idSeance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOpcvm, idSeance);
    }

    public CleSeanceOpcvm() {
    }

    public CleSeanceOpcvm(Long idOpcvm, Long idSeance) {
        this.idOpcvm = idOpcvm;
        this.idSeance = idSeance;
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
