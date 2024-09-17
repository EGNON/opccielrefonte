package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleNbreJoursAlerte implements Serializable {
    private Long idNbreJours;
    private Long idAlerte;

    public CleNbreJoursAlerte() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleNbreJoursAlerte that)) return false;
        return Objects.equals(idNbreJours, that.idNbreJours) && Objects.equals(idAlerte, that.idAlerte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNbreJours, idAlerte);
    }

    public Long getIdNbreJours() {
        return idNbreJours;
    }

    public void setIdNbreJours(Long idNbreJours) {
        this.idNbreJours = idNbreJours;
    }

    public Long getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }
}
