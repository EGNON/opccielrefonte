package com.ged.entity.crm;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleAgentConcerne implements Serializable {
    private Long idPersonne;
    private Long idRdv;

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(Long idRdv) {
        this.idRdv = idRdv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleAgentConcerne that = (CleAgentConcerne) o;
        return Objects.equals(idPersonne, that.idPersonne) && Objects.equals(idRdv, that.idRdv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, idRdv);
    }
}
