package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeFormuleDto {
    private String codeTypeFormule;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private String userLogin;
    private Long numLigne;
    private Boolean supprimer;
    private byte[] rowvers;
    public TypeFormuleDto() {
    }

    public String getCodeTypeFormule() {
        return codeTypeFormule;
    }

    public void setCodeTypeFormule(String codeTypeFormule) {
        this.codeTypeFormule = codeTypeFormule;
    }

    public LocalDateTime getDateCreationServeur() {
        return dateCreationServeur;
    }

    public void setDateCreationServeur(LocalDateTime dateCreationServeur) {
        this.dateCreationServeur = dateCreationServeur;
    }

    public LocalDateTime getDateDernModifServeur() {
        return dateDernModifServeur;
    }

    public void setDateDernModifServeur(LocalDateTime dateDernModifServeur) {
        this.dateDernModifServeur = dateDernModifServeur;
    }

    public LocalDateTime getDateDernModifClient() {
        return dateDernModifClient;
    }

    public void setDateDernModifClient(LocalDateTime dateDernModifClient) {
        this.dateDernModifClient = dateDernModifClient;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    public Boolean getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public byte[] getRowvers() {
        return rowvers;
    }

    public void setRowvers(byte[] rowvers) {
        this.rowvers = rowvers;
    }
}
