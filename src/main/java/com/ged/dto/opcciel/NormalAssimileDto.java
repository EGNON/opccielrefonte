package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NormalAssimileDto {

    private String codeNormalAssimile;

    private String libelleNormalAssimile;

    private LocalDateTime dateCreationServeur;

    private LocalDateTime dateDernModifServeur;

    private LocalDateTime dateDernModifClient;

    private long numLigne;

    private boolean supprimer;

    private LocalDateTime rowvers;

    private String userLogin;

    public NormalAssimileDto() {
    }

    public NormalAssimileDto(String codeNormalAssimile, String libelleNormalAssimile) {
        this.codeNormalAssimile = codeNormalAssimile;
        this.libelleNormalAssimile = libelleNormalAssimile;
    }

    public String getCodeNormalAssimile() {
        return codeNormalAssimile;
    }

    public void setCodeNormalAssimile(String codeNormalAssimile) {
        this.codeNormalAssimile = codeNormalAssimile;
    }

    public String getLibelleNormalAssimile() {
        return libelleNormalAssimile;
    }

    public void setLibelleNormalAssimile(String libelleNormalAssimile) {
        this.libelleNormalAssimile = libelleNormalAssimile;
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

    public long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(long numLigne) {
        this.numLigne = numLigne;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public LocalDateTime getRowvers() {
        return rowvers;
    }

    public void setRowvers(LocalDateTime rowvers) {
        this.rowvers = rowvers;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

}
