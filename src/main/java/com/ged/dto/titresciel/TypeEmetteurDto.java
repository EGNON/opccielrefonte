package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeEmetteurDto {
    
    private String codeTypeEmetteur;
    private String libelleTypeEmetteur;
    @CreationTimestamp
    private LocalDateTime dateCreationServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifClient;
    private long numLigne;
    private boolean supprimer;
    private LocalDateTime rowvers;
    private String userLogin;

    public TypeEmetteurDto() {
    }

    public TypeEmetteurDto(String codeTypeEmetteur, String libelleTypeEmetteur) {
        this.codeTypeEmetteur = codeTypeEmetteur;
        this.libelleTypeEmetteur = libelleTypeEmetteur;
    }

    public String getCodeTypeEmetteur() {
        return codeTypeEmetteur;
    }

    public void setCodeTypeEmetteur(String codeTypeEmetteur) {
        this.codeTypeEmetteur = codeTypeEmetteur;
    }

    public String getLibelleTypeEmetteur() {
        return libelleTypeEmetteur;
    }

    public void setLibelleTypeEmetteur(String libelleTypeEmetteur) {
        this.libelleTypeEmetteur = libelleTypeEmetteur;
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
