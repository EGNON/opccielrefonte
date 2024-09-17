package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleProfilCommissionSousRach implements Serializable {
    private String codeProfil;
    private Long idOpcvm;

    public CleProfilCommissionSousRach() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleProfilCommissionSousRach that)) return false;
        return Objects.equals(getCodeProfil(), that.getCodeProfil()) && Objects.equals(getIdOpcvm(), that.getIdOpcvm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeProfil(), getIdOpcvm());
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
