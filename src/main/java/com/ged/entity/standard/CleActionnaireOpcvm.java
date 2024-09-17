package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleActionnaireOpcvm implements Serializable {
    private Long idPersonne;
    private Long idOpcvm;

    public CleActionnaireOpcvm() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleActionnaireOpcvm that)) return false;
        return Objects.equals(getIdPersonne(), that.getIdPersonne()) && Objects.equals(getIdOpcvm(), that.getIdOpcvm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPersonne(), getIdOpcvm());
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }
}
