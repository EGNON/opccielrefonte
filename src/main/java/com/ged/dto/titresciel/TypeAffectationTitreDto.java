package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeAffectationTitreDto {
    private Long idTypeAffectation;
    private String libelleTypeAffectationVL;

    public Long getIdTypeAffectation() {
        return idTypeAffectation;
    }

    public void setIdTypeAffectation(Long idTypeAffectation) {
        this.idTypeAffectation = idTypeAffectation;
    }

    public String getLibelleTypeAffectationVL() {
        return libelleTypeAffectationVL;
    }

    public void setLibelleTypeAffectationVL(String libelleTypeAffectationVL) {
        this.libelleTypeAffectationVL = libelleTypeAffectationVL;
    }
}
