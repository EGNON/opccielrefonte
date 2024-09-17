package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeRubriqueDto extends Base {

    private String codeTypeRubrique;

    public TypeRubriqueDto() {
    }

    public String getCodeTypeRubrique() {
        return codeTypeRubrique;
    }

    public void setCodeTypeRubrique(String codeTypeRubrique) {
        this.codeTypeRubrique = codeTypeRubrique;
    }
}
