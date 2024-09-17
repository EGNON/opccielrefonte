package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleExercice implements Serializable {
    private Long idOpcvm;
    @Column(length = 16)
    private String codeExercice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleExercice that = (CleExercice) o;
        return Objects.equals(idOpcvm, that.idOpcvm) && Objects.equals(codeExercice, that.codeExercice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOpcvm, codeExercice);
    }

    public CleExercice() {
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(String codeExercice) {
        this.codeExercice = codeExercice;
    }
}
