package com.ged.entity.crm;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleObjectifAffecte implements Serializable {
    private Long idCategorie;
    private Long idPeriodicite;
    private Long idIndicateur;
    private Long idAffectation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleObjectifAffecte that = (CleObjectifAffecte) o;
        return Objects.equals(idCategorie, that.idCategorie) && Objects.equals(idPeriodicite, that.idPeriodicite) && Objects.equals(idIndicateur, that.idIndicateur) && Objects.equals(idAffectation, that.idAffectation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategorie, idPeriodicite, idIndicateur, idAffectation);
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Long getIdPeriodicite() {
        return idPeriodicite;
    }

    public void setIdPeriodicite(Long idPeriodicite) {
        this.idPeriodicite = idPeriodicite;
    }

    public Long getIdIndicateur() {
        return idIndicateur;
    }

    public void setIdIndicateur(Long idIndicateur) {
        this.idIndicateur = idIndicateur;
    }

    public Long getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Long idAffectation) {
        this.idAffectation = idAffectation;
    }

    @Override
    public String toString() {
        return "CleObjectifAffecte{" +
                "idCategorie=" + idCategorie +
                ", idPeriodicite=" + idPeriodicite +
                ", idIndicateur=" + idIndicateur +
                ", idAffectation=" + idAffectation +
                '}';
    }
}
