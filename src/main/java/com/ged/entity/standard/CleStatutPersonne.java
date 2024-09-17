package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleStatutPersonne implements Serializable {
    private Long idPersonne;
    private Long idQualite;

    public CleStatutPersonne() {
    }

    public CleStatutPersonne(Long idPersonne, Long idQualite) {
        this.idPersonne = idPersonne;
        this.idQualite = idQualite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleStatutPersonne that = (CleStatutPersonne) o;
        return Objects.equals(idPersonne, that.idPersonne) && Objects.equals(idQualite, that.idQualite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, idQualite);
    }

    public long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public long getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(Long idQualite) {
        this.idQualite = idQualite;
    }

    @Override
    public String toString() {
        return "CleStatutPersonne{" +
                "idPersonne=" + idPersonne +
                ", idQualite=" + idQualite +
                '}';
    }
}
