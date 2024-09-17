package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommuneDto {
    private Long idCommune;
    private String libelleCommune;
    private DepartementDto departement;

    public Long getIdCommune() {
        return idCommune;
    }

    public void setIdCommune(Long idCommune) {
        this.idCommune = idCommune;
    }

    public String getLibelleCommune() {
        return libelleCommune;
    }

    public void setLibelleCommune(String libelleCommune) {
        this.libelleCommune = libelleCommune;
    }

    public DepartementDto getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementDto departement) {
        this.departement = departement;
    }
}
