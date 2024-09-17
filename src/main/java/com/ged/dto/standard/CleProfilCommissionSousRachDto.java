package com.ged.dto.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CleProfilCommissionSousRachDto implements Serializable {
    private String codeProfil;
    private Long idOpcvm;

    public CleProfilCommissionSousRachDto() {
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
