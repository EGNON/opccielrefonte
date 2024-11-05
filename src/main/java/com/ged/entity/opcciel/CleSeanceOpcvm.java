package com.ged.entity.opcciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleSeanceOpcvm implements Serializable {
    private Long idOpcvm;
    private Long idSeance;

    public CleSeanceOpcvm() {
    }

    public CleSeanceOpcvm(Long idOpcvm, Long idSeance) {
        this.idOpcvm = idOpcvm;
        this.idSeance = idSeance;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }
}
