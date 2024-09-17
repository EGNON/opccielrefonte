package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeObligationDto {

    private Long idTypeObligation;
    private String codeTypeObligation;
    private String libelleTypeObligation;

    public Long getIdTypeObligation() {
        return idTypeObligation;
    }

    public void setIdTypeObligation(Long idTypeObligation) {
        this.idTypeObligation = idTypeObligation;
    }

    public String getCodeTypeObligation() {
        return codeTypeObligation;
    }

    public void setCodeTypeObligation(String codeTypeObligation) {
        this.codeTypeObligation = codeTypeObligation;
    }

    public String getLibelleTypeObligation() {
        return libelleTypeObligation;
    }

    public void setLibelleTypeObligation(String libelleTypeObligation) {
        this.libelleTypeObligation = libelleTypeObligation;
    }
}
