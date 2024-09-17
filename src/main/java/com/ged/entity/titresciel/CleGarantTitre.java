package com.ged.entity.titresciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleGarantTitre implements Serializable {
    private Long idTitre;
    private Long idGarant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleGarantTitre that)) return false;
        return Objects.equals(getIdTitre(), that.getIdTitre()) && Objects.equals(getIdGarant(), that.getIdGarant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTitre(), getIdGarant());
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public Long getIdGarant() {
        return idGarant;
    }

    public void setIdGarant(Long idGarant) {
        this.idGarant = idGarant;
    }
}
