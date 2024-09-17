package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleOperationFormule implements Serializable {
    private Long idOperation;
    private Long idFormule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleOperationFormule that = (CleOperationFormule) o;
        return Objects.equals(idOperation, that.idOperation) && Objects.equals(idFormule, that.idFormule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperation, idFormule);
    }

    public CleOperationFormule() {
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdFormule() {
        return idFormule;
    }

    public void setIdFormule(Long idFormule) {
        this.idFormule = idFormule;
    }
}
