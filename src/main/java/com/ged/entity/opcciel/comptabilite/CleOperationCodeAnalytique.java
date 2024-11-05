package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleOperationCodeAnalytique implements Serializable {
    private Long idOperation;
    private String codeAnalytique;
    private String typeCodeAnalytique;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleOperationCodeAnalytique that = (CleOperationCodeAnalytique) o;
        return Objects.equals(idOperation, that.idOperation) && Objects.equals(codeAnalytique, that.codeAnalytique) && Objects.equals(typeCodeAnalytique, that.typeCodeAnalytique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperation, codeAnalytique, typeCodeAnalytique);
    }

    public CleOperationCodeAnalytique() {
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public String getCodeAnalytique() {
        return codeAnalytique;
    }

    public void setCodeAnalytique(String codeAnalytique) {
        this.codeAnalytique = codeAnalytique;
    }

    public String getTypeCodeAnalytique() {
        return typeCodeAnalytique;
    }

    public void setTypeCodeAnalytique(String typeCodeAnalytique) {
        this.typeCodeAnalytique = typeCodeAnalytique;
    }
}
