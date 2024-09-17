package com.ged.entity.titresciel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleAdjudicationTcn implements Serializable {
    private Long idEmetteur;
    @Column(length = 16)
    private String numeroSouscription;

    public CleAdjudicationTcn() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleAdjudicationTcn that)) return false;
        return Objects.equals(getIdEmetteur(), that.getIdEmetteur()) && Objects.equals(getNumeroSouscription(), that.getNumeroSouscription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdEmetteur(), getNumeroSouscription());
    }

    public Long getIdEmetteur() {
        return idEmetteur;
    }

    public void setIdEmetteur(Long idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public String getNumeroSouscription() {
        return numeroSouscription;
    }

    public void setNumeroSouscription(String numeroSouscription) {
        this.numeroSouscription = numeroSouscription;
    }
}
