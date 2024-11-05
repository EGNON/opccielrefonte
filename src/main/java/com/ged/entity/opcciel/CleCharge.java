package com.ged.entity.opcciel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleCharge implements Serializable {
    private Long idOpcvm;
    private String codeCharge;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleCharge cleCharge = (CleCharge) o;
        return Objects.equals(idOpcvm, cleCharge.idOpcvm) && Objects.equals(codeCharge, cleCharge.codeCharge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOpcvm, codeCharge);
    }

    public CleCharge() {
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }
}
