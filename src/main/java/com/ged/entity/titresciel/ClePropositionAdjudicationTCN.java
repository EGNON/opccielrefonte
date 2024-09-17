package com.ged.entity.titresciel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClePropositionAdjudicationTCN implements Serializable {
    private Long idEmetteur;
    @Column(length = 16)
    private String numeroSouscription;
    private double taux;

    public ClePropositionAdjudicationTCN() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClePropositionAdjudicationTCN that)) return false;
        return Double.compare(that.getTaux(), getTaux()) == 0 && Objects.equals(getIdEmetteur(), that.getIdEmetteur()) && Objects.equals(getNumeroSouscription(), that.getNumeroSouscription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdEmetteur(), getNumeroSouscription(), getTaux());
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

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
