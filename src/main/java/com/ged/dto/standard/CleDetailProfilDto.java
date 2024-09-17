package com.ged.dto.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.math.BigDecimal;

public class CleDetailProfilDto implements Serializable {
    private String codeProfil;
    private Long idOpcvm;
    private BigDecimal borneInferieur;

    public CleDetailProfilDto() {
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
