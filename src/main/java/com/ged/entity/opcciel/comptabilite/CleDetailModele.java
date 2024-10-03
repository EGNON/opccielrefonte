package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleDetailModele implements Serializable {
    @Column(length = 16)
    private String coodeModeleEcriture;
    @Column(length = 16)
    private String numCompteComptable;
    private int numeroOrdre;


    public CleDetailModele() {
    }

    public String getCoodeModeleEcriture() {
        return coodeModeleEcriture;
    }

    public void setCoodeModeleEcriture(String coodeModeleEcriture) {
        this.coodeModeleEcriture = coodeModeleEcriture;
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
