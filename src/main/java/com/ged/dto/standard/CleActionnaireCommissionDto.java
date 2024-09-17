package com.ged.dto.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

public class CleActionnaireCommissionDto implements Serializable {
    private Long idPersonne;
    private Long idOpcvm;
    private String typeCommission;

    public CleActionnaireCommissionDto() {
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(String typeCommission) {
        this.typeCommission = typeCommission;
    }
}
