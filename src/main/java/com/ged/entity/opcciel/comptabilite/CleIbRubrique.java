package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleIbRubrique implements Serializable {
    private String codeIB;
    private String codeRubrique;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleIbRubrique that = (CleIbRubrique) o;
        return Objects.equals(codeIB, that.codeIB) && Objects.equals(codeRubrique, that.codeRubrique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeIB, codeRubrique);
    }

    public CleIbRubrique() {
    }

    public String getCodeIB() {
        return codeIB;
    }

    public void setCodeIB(String codeIB) {
        this.codeIB = codeIB;
    }

    public String getCodeRubrique() {
        return codeRubrique;
    }

    public void setCodeRubrique(String codeRubrique) {
        this.codeRubrique = codeRubrique;
    }
}
