package com.ged.entity.standard;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.apache.poi.hpsf.Decimal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class CleDetailProfil implements Serializable {
    private String codeProfil;
    private Long idOpcvm;
    @Column(precision = 18, scale = 6)
    private BigDecimal borneInferieur;

    public CleDetailProfil() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleDetailProfil that)) return false;
        return Objects.equals(getCodeProfil(), that.getCodeProfil()) && Objects.equals(getIdOpcvm(), that.getIdOpcvm()) && Objects.equals(getBorneInferieur(), that.getBorneInferieur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeProfil(), getIdOpcvm(), getBorneInferieur());
    }

    public BigDecimal getBorneInferieur() {
        return borneInferieur;
    }

    public void setBorneInferieur(BigDecimal borneInferieur) {
        this.borneInferieur = borneInferieur;
    }

    public String getCodeProfil() {
        return codeProfil;
    }

    public void setCodeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }
}
