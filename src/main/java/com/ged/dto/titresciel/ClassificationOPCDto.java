package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificationOPCDto {
    private String codeClassification;
    private String libelleClassification;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private long numLigne;
    private boolean supprimer;
    private LocalDateTime rowvers;
    private String userLogin;
    private Set<OpcvmDto> opcvms = new HashSet<>();

    public ClassificationOPCDto() {
    }

    public ClassificationOPCDto(String codeClassification, String libelleClassification) {
        this.codeClassification = codeClassification;
        this.libelleClassification = libelleClassification;
    }

    public String getCodeClassification() {
        return codeClassification;
    }

    public void setCodeClassification(String codeClassification) {
        this.codeClassification = codeClassification;
    }

    public String getLibelleClassification() {
        return libelleClassification;
    }

    public void setLibelleClassification(String libelleClassification) {
        this.libelleClassification = libelleClassification;
    }

    public Set<OpcvmDto> getOpcvms() {
        return opcvms;
    }

    public void setOpcvms(Set<OpcvmDto> opcvms) {
        this.opcvms = opcvms;
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
