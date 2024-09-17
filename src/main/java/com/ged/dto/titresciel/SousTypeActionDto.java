package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SousTypeActionDto {

    private Long idSousTypeAction;
    private String codeSousTypeAction;
    private String libelleSousTypeAction;

    public Long getIdSousTypeAction() {
        return idSousTypeAction;
    }

    public void setIdSousTypeAction(Long idSousTypeAction) {
        this.idSousTypeAction = idSousTypeAction;
    }

    public String getCodeSousTypeAction() {
        return codeSousTypeAction;
    }

    public void setCodeSousTypeAction(String codeSousTypeAction) {
        this.codeSousTypeAction = codeSousTypeAction;
    }

    public String getLibelleSousTypeAction() {
        return libelleSousTypeAction;
    }

    public void setLibelleSousTypeAction(String libelleSousTypeAction) {
        this.libelleSousTypeAction = libelleSousTypeAction;
    }
}
