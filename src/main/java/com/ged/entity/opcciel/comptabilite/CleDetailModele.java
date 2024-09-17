package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleDetailModele implements Serializable {
    @Column(length = 16)
    private String codeModeleEcriture;
    @Column(length = 16)
    private String numCompteComptable;
    private int numeroOrdre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleDetailModele that = (CleDetailModele) o;
        return numeroOrdre == that.numeroOrdre && Objects.equals(codeModeleEcriture, that.codeModeleEcriture) && Objects.equals(numCompteComptable, that.numCompteComptable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeModeleEcriture, numCompteComptable, numeroOrdre);
    }

    public CleDetailModele() {
    }

    public String getCodeModeleEcriture() {
        return codeModeleEcriture;
    }

    public void setCodeModeleEcriture(String codeModeleEcriture) {
        this.codeModeleEcriture = codeModeleEcriture;
    }

    public String getNumCompteComptable() {
        return numCompteComptable;
    }

    public void setNumCompteComptable(String numCompteComptable) {
        this.numCompteComptable = numCompteComptable;
    }

    public int getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(int numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }
}
