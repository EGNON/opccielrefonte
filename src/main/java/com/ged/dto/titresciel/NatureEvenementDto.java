package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NatureEvenementDto {

    private Long idNatureEvenement;

    private String libelleNatureEvenement;

    private LocalDateTime dateCreationServeur;

    private LocalDateTime dateDernModifServeur;

    private LocalDateTime dateDernModifClient;

    private long numLigne;

    private boolean supprimer;

    private LocalDateTime rowvers;

    private String userLogin;

    public NatureEvenementDto() {
    }

    public NatureEvenementDto(String libelleNatureEvenement) {
        this.libelleNatureEvenement = libelleNatureEvenement;
    }

    public Long getIdNatureEvenement() {
        return idNatureEvenement;
    }

    public void setIdNatureEvenement(Long idNatureEvenement) {
        this.idNatureEvenement = idNatureEvenement;
    }

    public String getLibelleNatureEvenement() {
        return libelleNatureEvenement;
    }

    public void setLibelleNatureEvenement(String libelleNatureEvenement) {
        this.libelleNatureEvenement = libelleNatureEvenement;
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
