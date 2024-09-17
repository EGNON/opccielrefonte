package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypePositionDto extends Base {

    private String codeTypePosition;
    private String libelleTypePosition;

    public TypePositionDto() {
    }

    public String getCodeTypePosition() {
        return codeTypePosition;
    }

    public void setCodeTypePosition(String codeTypePosition) {
        this.codeTypePosition = codeTypePosition;
    }

    public String getLibelleTypePosition() {
        return libelleTypePosition;
    }

    public void setLibelleTypePosition(String libelleTypePosition) {
        this.libelleTypePosition = libelleTypePosition;
    }
}
