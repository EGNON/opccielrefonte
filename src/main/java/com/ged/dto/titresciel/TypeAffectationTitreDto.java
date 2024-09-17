package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeAffectationTitreDto {
    private Long idTypeAffectation;
    private String libelleTypeAffectation;

    public Long getIdTypeAffectation() {
        return idTypeAffectation;
    }

    public void setIdTypeAffectation(Long idTypeAffectation) {
        this.idTypeAffectation = idTypeAffectation;
    }

    public String getLibelleTypeAffectation() {
        return libelleTypeAffectation;
    }

    public void setLibelleTypeAffectation(String libelleTypeAffectation) {
        this.libelleTypeAffectation = libelleTypeAffectation;
    }
}
