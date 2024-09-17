package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDto {
    private String codePlace;

    private String libellePlace;

    private LocalDateTime dateCreationServeur;

    private LocalDateTime dateDernModifServeur;

    private LocalDateTime dateDernModifClient;

    private long numLigne;

    private boolean supprimer;

    private LocalDateTime rowvers;

    private String userLogin;


    public PlaceDto() {
    }

    public PlaceDto(String codePlace, String libellePlace) {
        this.codePlace = codePlace;
        this.libellePlace = libellePlace;
    }

    public String getCodePlace() {
        return codePlace;
    }

    public void setCodePlace(String codePlace) {
        this.codePlace = codePlace;
    }

    public String getLibellePlace() {
        return libellePlace;
    }

    public void setLibellePlace(String libellePlace) {
        this.libellePlace = libellePlace;
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
