package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeActionDto {

    private Long idTypeAction;
    private String codeTypeAction;
    private String libelleTypeAction;

    public Long getIdTypeAction() {
        return idTypeAction;
    }

    public void setIdTypeAction(Long idTypeAction) {
        this.idTypeAction = idTypeAction;
    }

    public String getCodeTypeAction() {
        return codeTypeAction;
    }

    public void setCodeTypeAction(String codeTypeAction) {
        this.codeTypeAction = codeTypeAction;
    }

    public String getLibelleTypeAction() {
        return libelleTypeAction;
    }

    public void setLibelleTypeAction(String libelleTypeAction) {
        this.libelleTypeAction = libelleTypeAction;
    }
}
