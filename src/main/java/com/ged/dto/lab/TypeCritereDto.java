package com.ged.dto.lab;

import com.ged.dto.standard.DiffusionAlerteDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TypeCritereDto {
    private Long idTypeCritere;
    private String libelleTypeCritere;
    private LocalDateTime dateCreationServeur;
    private Date dateDernModifServeur;
    private Date dateDernModifClient;
    private long numLigne;
    private boolean supprimer;
    private LocalDateTime rowvers;
    private String userLogin;
    private Set<DiffusionAlerteDto> diffusionAlerteDtos =new HashSet<>();
    public TypeCritereDto() {
    }

    public TypeCritereDto(Long idTypeCritere, String libelleTypeCritere, String userLogin) {
        this.idTypeCritere = idTypeCritere;
        this.libelleTypeCritere = libelleTypeCritere;
        this.userLogin = userLogin;
    }

    public Set<DiffusionAlerteDto> getDiffusionAlerteDtos() {
        return diffusionAlerteDtos;
    }

    public void setDiffusionAlerteDtos(Set<DiffusionAlerteDto> diffusionAlerteDtos) {
        this.diffusionAlerteDtos = diffusionAlerteDtos;
    }

    public Long getIdTypeCritere() {
        return idTypeCritere;
    }

    public void setIdTypeCritere(Long idTypeCritere) {
        this.idTypeCritere = idTypeCritere;
    }

    public String getLibelleTypeCritere() {
        return libelleTypeCritere;
    }

    public void setLibelleTypeCritere(String libelleTypeCritere) {
        this.libelleTypeCritere = libelleTypeCritere;
    }

    public LocalDateTime getDateCreationServeur() {
        return dateCreationServeur;
    }

    public void setDateCreationServeur(LocalDateTime dateCreationServeur) {
        this.dateCreationServeur = dateCreationServeur;
    }

    public Date getDateDernModifServeur() {
        return dateDernModifServeur;
    }

    public void setDateDernModifServeur(Date dateDernModifServeur) {
        this.dateDernModifServeur = dateDernModifServeur;
    }

    public Date getDateDernModifClient() {
        return dateDernModifClient;
    }

    public void setDateDernModifClient(Date dateDernModifClient) {
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

    @Override
    public String toString() {
        return "TypeCritere [" +
                "idTypeCritere=" + idTypeCritere +
                ", libelleTypeCritere='" + libelleTypeCritere + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ']';
    }
}
