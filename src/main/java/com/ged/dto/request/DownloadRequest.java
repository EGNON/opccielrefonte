package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.security.UtilisateurDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DownloadRequest {
    private Long idOpcvm;
    private Long idSeance;
    private Long niveau;
    private UtilisateurDto user;

    public DownloadRequest() {
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public Long getNiveau() {
        return niveau;
    }

    public void setNiveau(Long niveau) {
        this.niveau = niveau;
    }

    public UtilisateurDto getUser() {
        return user;
    }

    public void setUser(UtilisateurDto user) {
        this.user = user;
    }
}
