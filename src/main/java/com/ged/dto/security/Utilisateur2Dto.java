package com.ged.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonnelDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Utilisateur2Dto extends PersonnelDto {
    private Long id;
    private Long IdPersonne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getIdPersonne() {
        return IdPersonne;
    }

    @Override
    public void setIdPersonne(Long idPersonne) {
        IdPersonne = idPersonne;
    }
}
