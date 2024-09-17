package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleProtoAlerte implements Serializable {
    private Long idAlerte;
    private Long idModele;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleProtoAlerte that)) return false;
        return Objects.equals(idAlerte, that.idAlerte) && Objects.equals(idModele, that.idModele);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlerte, idModele);
    }

    public Long getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }

    public Long getIdModele() {
        return idModele;
    }

    public void setIdModele(Long idModele) {
        this.idModele = idModele;
    }
}
