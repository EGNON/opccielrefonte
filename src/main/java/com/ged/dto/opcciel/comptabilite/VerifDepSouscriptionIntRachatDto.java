package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifDepSouscriptionIntRachatDto {
    private Long idOpcvm;
    private String codeNatureOperation;
    private String niveau;
    private String userLoginVerif;

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getUserLoginVerif() {
        return userLoginVerif;
    }

    public void setUserLoginVerif(String userLoginVerif) {
        this.userLoginVerif = userLoginVerif;
    }
}
