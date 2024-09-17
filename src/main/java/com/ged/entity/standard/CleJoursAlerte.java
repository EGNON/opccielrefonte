package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleJoursAlerte implements Serializable {
    private Long idJours;
    private Long idAlerte;

    public CleJoursAlerte() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleJoursAlerte that)) return false;
        return Objects.equals(idJours, that.idJours) && Objects.equals(idAlerte, that.idAlerte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJours, idAlerte);
    }

    public Long getIdJours() {
        return idJours;
    }

    public void setIdJours(Long idJours) {
        this.idJours = idJours;
    }

    public Long getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }
}
