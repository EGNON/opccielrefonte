package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonneDto;
import com.ged.entity.Base;
import com.ged.entity.opcciel.CleOrdre;
import com.ged.entity.opcciel.Ordre;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdreSignataireDto extends Base {
    private CleOrdre idOrdre;
    private OrdreDto ordre;
    private PersonneDto personne;

    public OrdreSignataireDto() {
    }

    public CleOrdre getIdOrdre() {
        return idOrdre;
    }

    public void setIdOrdre(CleOrdre idOrdre) {
        this.idOrdre = idOrdre;
    }

    public OrdreDto getOrdre() {
        return ordre;
    }

    public void setOrdre(OrdreDto ordre) {
        this.ordre = ordre;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }
}
