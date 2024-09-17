package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormeJuridiqueDto {
    private String codeFormeJuridique;

    private String libelleFormeJuridique;

    public FormeJuridiqueDto() {
    }

    public FormeJuridiqueDto(String codeFormeJuridique, String libelleFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
        this.libelleFormeJuridique = libelleFormeJuridique;
    }

    public String getCodeFormeJuridique() {
        return codeFormeJuridique;
    }

    public void setCodeFormeJuridique(String codeFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
    }

    public String getLibelleFormeJuridique() {
        return libelleFormeJuridique;
    }

    public void setLibelleFormeJuridique(String libelleFormeJuridique) {
        this.libelleFormeJuridique = libelleFormeJuridique;
    }
}
