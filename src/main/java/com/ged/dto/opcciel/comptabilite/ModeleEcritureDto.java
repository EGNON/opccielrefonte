package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeleEcritureDto {
    private String codeModeleEcriture;
	private String libelleModeleEcriture;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private String userLogin;
    private Long numLigne;
    private Boolean supprimer;
    private byte[] rowvers;
    private Set<ModeleEcritureFormuleDto> modeleEcritureFormules;
//    private Set<ModeleEcritureNatureOperationDto> modeleEcritureNatureOperations;

    public ModeleEcritureDto() {
    }

    public Set<ModeleEcritureFormuleDto> getModeleEcritureFormules() {
        return modeleEcritureFormules;
    }

    public void setModeleEcritureFormules(Set<ModeleEcritureFormuleDto> modeleEcritureFormules) {
        this.modeleEcritureFormules = modeleEcritureFormules;
    }

//    public Set<ModeleEcritureNatureOperationDto> getModeleEcritureNatureOperations() {
//        return modeleEcritureNatureOperations;
//    }
//
//    public void setModeleEcritureNatureOperations(Set<ModeleEcritureNatureOperationDto> modeleEcritureNatureOperations) {
//        this.modeleEcritureNatureOperations = modeleEcritureNatureOperations;
//    }

    public String getCodeModeleEcriture() {
        return codeModeleEcriture;
    }

    public void setCodeModeleEcriture(String codeModeleEcriture) {
        this.codeModeleEcriture = codeModeleEcriture;
    }

    public String getLibelleModeleEcriture() {
        return libelleModeleEcriture;
    }

    public void setLibelleModeleEcriture(String libelleModeleEcriture) {
        this.libelleModeleEcriture = libelleModeleEcriture;
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
