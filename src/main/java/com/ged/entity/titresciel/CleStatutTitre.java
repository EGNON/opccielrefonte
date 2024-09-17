package com.ged.entity.titresciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleStatutTitre implements Serializable {
    private Long idTitre;
    private Long idQualite;

    public CleStatutTitre() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleStatutTitre that)) return false;
        return Objects.equals(getIdTitre(), that.getIdTitre()) && Objects.equals(getIdQualite(), that.getIdQualite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTitre(), getIdQualite());
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public Long getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(Long idQualite) {
        this.idQualite = idQualite;
    }
}
