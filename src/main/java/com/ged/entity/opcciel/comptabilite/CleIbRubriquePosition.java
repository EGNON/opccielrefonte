package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleIbRubriquePosition implements Serializable {
    private String codeIB;
    private String codeRubrique;
    private String codePosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleIbRubriquePosition that = (CleIbRubriquePosition) o;
        return Objects.equals(codeIB, that.codeIB) && Objects.equals(codeRubrique, that.codeRubrique) && Objects.equals(codePosition, that.codePosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeIB, codeRubrique, codePosition);
    }

    public CleIbRubriquePosition() {
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

    public String getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(String codePosition) {
        this.codePosition = codePosition;
    }
}
