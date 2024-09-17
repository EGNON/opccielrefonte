package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleDiffusionAlerte implements Serializable {
    private Long idPersonne;
    private Long idModele;
    private Long idAlerte;

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdModele() {
        return idModele;
    }

    public void setIdModele(Long idModele) {
        this.idModele = idModele;
    }

    public Long getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleDiffusionAlerte that = (CleDiffusionAlerte) o;
        return Objects.equals(idPersonne, that.idPersonne) && Objects.equals(idModele, that.idModele) && Objects.equals(idAlerte, that.idAlerte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, idModele, idAlerte);
    }
}
