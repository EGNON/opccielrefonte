package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeTitreDto {
    private Long idTypeTitre;
    private String codeTypeTitre;
    private String libelleTypeTitre;
    private ClasseTitreDto classeTitre;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private long numLigne;
    private boolean supprimer;
    private LocalDateTime rowvers;
    private String userLogin;

    public TypeTitreDto() {
    }

    public Long getIdTypeTitre() {
        return idTypeTitre;
    }

    public void setIdTypeTitre(Long idTypeTitre) {
        this.idTypeTitre = idTypeTitre;
    }

    public String getCodeTypeTitre() {
        return codeTypeTitre;
    }

    public void setCodeTypeTitre(String codeTypeTitre) {
        this.codeTypeTitre = codeTypeTitre;
    }

    public String getLibelleTypeTitre() {
        return libelleTypeTitre;
    }

    public void setLibelleTypeTitre(String libelleTypeTitre) {
        this.libelleTypeTitre = libelleTypeTitre;
    }

    public ClasseTitreDto getClasseTitre() {
        return classeTitre;
    }

    public void setClasseTitre(ClasseTitreDto classeTitre) {
        this.classeTitre = classeTitre;
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
