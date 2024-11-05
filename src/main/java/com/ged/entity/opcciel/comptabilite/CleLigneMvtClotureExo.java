package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleLigneMvtClotureExo implements Serializable {
    private Long idOpcvm;
    private String codeExercice;
    private int etape;
    private int numeroOrdeEtape;
    private String numeroCompteComptable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleLigneMvtClotureExo that = (CleLigneMvtClotureExo) o;
        return etape == that.etape && numeroOrdeEtape == that.numeroOrdeEtape && Objects.equals(idOpcvm, that.idOpcvm) && Objects.equals(codeExercice, that.codeExercice) && Objects.equals(numeroCompteComptable, that.numeroCompteComptable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOpcvm, codeExercice, etape, numeroOrdeEtape, numeroCompteComptable);
    }

    public CleLigneMvtClotureExo() {
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

    public int getEtape() {
        return etape;
    }

    public void setEtape(int etape) {
        this.etape = etape;
    }

    public int getNumeroOrdeEtape() {
        return numeroOrdeEtape;
    }

    public void setNumeroOrdeEtape(int numeroOrdeEtape) {
        this.numeroOrdeEtape = numeroOrdeEtape;
    }

    public String getNumeroCompteComptable() {
        return numeroCompteComptable;
    }

    public void setNumeroCompteComptable(String numeroCompteComptable) {
        this.numeroCompteComptable = numeroCompteComptable;
    }
}
