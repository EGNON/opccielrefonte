package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleOperationJournal implements Serializable {
    private Long idOperation;
    private String codeJournal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleOperationJournal that = (CleOperationJournal) o;
        return Objects.equals(idOperation, that.idOperation) && Objects.equals(codeJournal, that.codeJournal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperation, codeJournal);
    }

    public CleOperationJournal() {
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public String getCodeJournal() {
        return codeJournal;
    }

    public void setCodeJournal(String codeJournal) {
        this.codeJournal = codeJournal;
    }
}
