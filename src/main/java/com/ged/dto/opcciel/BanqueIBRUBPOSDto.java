package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.IbDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BanqueIBRUBPOSDto {
    private IbDto ib;

    private String codeRubrique;
    private String codePosition;

    public BanqueIBRUBPOSDto() {
    }

    public IbDto getIb() {
        return ib;
    }

    public void setIb(IbDto ib) {
        this.ib = ib;
    }

    public String getCodeRubrique() {
        return codeRubrique;
    }

    public void setCodeRubrique(String codeRubrique) {
        this.codeRubrique = codeRubrique;
    }

    public String getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(String codePosition) {
        this.codePosition = codePosition;
    }
}
