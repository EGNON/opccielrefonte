package com.ged.dto.standard.revuecompte;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

public class CleNumeroCompteDto{
    private Long idPersonne;
    private Long idSousTypeCompte;

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
