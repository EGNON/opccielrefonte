package com.ged.entity.standard.revuecompte;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleNumeroCompte implements Serializable {
    private Long idPersonne;
    private Long idSousTypeCompte;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleNumeroCompte that)) return false;
        return Objects.equals(getIdPersonne(), that.getIdPersonne()) && Objects.equals(getIdSousTypeCompte(), that.getIdSousTypeCompte());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPersonne(), getIdSousTypeCompte());
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdSousTypeCompte() {
        return idSousTypeCompte;
    }

    public void setIdSousTypeCompte(Long idSousTypeCompte) {
        this.idSousTypeCompte = idSousTypeCompte;
    }
}
