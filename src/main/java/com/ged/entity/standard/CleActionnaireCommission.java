package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleActionnaireCommission implements Serializable {
    private Long idPersonne;
    private Long idOpcvm;
    private String typeCommission;

    public CleActionnaireCommission() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleActionnaireCommission that)) return false;
        return Objects.equals(getIdPersonne(), that.getIdPersonne()) && Objects.equals(getIdOpcvm(), that.getIdOpcvm()) && Objects.equals(getTypeCommission(), that.getTypeCommission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPersonne(), getIdOpcvm(), getTypeCommission());
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

    public String getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(String typeCommission) {
        this.typeCommission = typeCommission;
    }
}
