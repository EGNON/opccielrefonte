package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClePersonnePhysiquePays implements Serializable {
    private Long idPersonne;
    private Long idPays;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClePersonnePhysiquePays that)) return false;
        return Objects.equals(idPersonne, that.idPersonne) && Objects.equals(idPays, that.idPays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, idPays);
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdPays() {
        return idPays;
    }

    public void setIdPays(Long idPays) {
        this.idPays = idPays;
    }
}
