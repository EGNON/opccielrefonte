package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormeJuridiqueOpcDto {
    private Long idFormeJuridiqueOpc;
    private String codeFormeJuridiqueOpc;
    private String libelleFormeJuridiqueOpc;

    public Long getIdFormeJuridiqueOpc() {
        return idFormeJuridiqueOpc;
    }

    public void setIdFormeJuridiqueOpc(Long idFormeJuridiqueOpc) {
        this.idFormeJuridiqueOpc = idFormeJuridiqueOpc;
    }

    public String getCodeFormeJuridiqueOpc() {
        return codeFormeJuridiqueOpc;
    }

    public void setCodeFormeJuridiqueOpc(String codeFormeJuridiqueOpc) {
        this.codeFormeJuridiqueOpc = codeFormeJuridiqueOpc;
    }

    public String getLibelleFormeJuridiqueOpc() {
        return libelleFormeJuridiqueOpc;
    }

    public void setLibelleFormeJuridiqueOpc(String libelleFormeJuridiqueOpc) {
        this.libelleFormeJuridiqueOpc = libelleFormeJuridiqueOpc;
    }
}
