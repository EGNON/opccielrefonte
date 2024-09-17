package com.ged.entity.opcciel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleBanqueIBRUBPOS implements Serializable {
    @Column(length = 20)
    private String idBanque;
    @Column(length = 20)
    private String codeApplication;
    @Column(length = 20)
    private String codeIB;
    @Column(length = 20)
    private String codeRubrique;
    @Column(length = 20)
    private String codePosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleBanqueIBRUBPOS that = (CleBanqueIBRUBPOS) o;
        return Objects.equals(idBanque, that.idBanque) && Objects.equals(codeApplication, that.codeApplication) && Objects.equals(codeIB, that.codeIB) && Objects.equals(codeRubrique, that.codeRubrique) && Objects.equals(codePosition, that.codePosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanque, codeApplication, codeIB, codeRubrique, codePosition);
    }

    public CleBanqueIBRUBPOS() {
    }

    public String getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(String idBanque) {
        this.idBanque = idBanque;
    }

    public String getCodeApplication() {
        return codeApplication;
    }

    public void setCodeApplication(String codeApplication) {
        this.codeApplication = codeApplication;
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
