package com.ged.dto.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

public class CleActionnaireOpcvmDto implements Serializable {
    private Long idPersonne;
    private Long idOpcvm;

    public CleActionnaireOpcvmDto() {
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
}
