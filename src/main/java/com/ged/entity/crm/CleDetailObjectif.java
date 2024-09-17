package com.ged.entity.crm;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleDetailObjectif implements Serializable {
    private Long idCategorie;
    private Long idPeriodicite;
    private Long idIndicateur;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleDetailObjectif that = (CleDetailObjectif) o;
        return Objects.equals(idCategorie, that.idCategorie) && Objects.equals(idPeriodicite, that.idPeriodicite) && Objects.equals(idIndicateur, that.idIndicateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategorie, idPeriodicite, idIndicateur);
    }
}
