package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleTempsAlerte implements Serializable {
    private Long idAlerte;
    private Long idTemps;

    public CleTempsAlerte() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleTempsAlerte that)) return false;
        return Objects.equals(idAlerte, that.idAlerte) && Objects.equals(idTemps, that.idTemps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlerte, idTemps);
    }

    public Long getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }

    public Long getIdTemps() {
        return idTemps;
    }

    public void setIdTemps(Long idTemps) {
        this.idTemps = idTemps;
    }
}
