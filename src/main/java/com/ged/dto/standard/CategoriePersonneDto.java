package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.DetailObjectifDto;
import com.ged.dto.crm.ObjectifReelDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriePersonneDto {
    private Long idCategorie;
    private String libelle;
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
    private Set<DetailObjectifDto> detailObjectifDtos = new HashSet<>();
    private Set<ObjectifReelDto> objectifReelDtos;
    public CategoriePersonneDto() {
    }

    public CategoriePersonneDto(String libelle, String userLogin) {
        this.libelle = libelle;
        this.userLogin = userLogin;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public Set<DetailObjectifDto> getDetailObjectifDtos() {
        return detailObjectifDtos;
    }

    public void setDetailObjectifDtos(Set<DetailObjectifDto> detailObjectifDtos) {
        this.detailObjectifDtos = detailObjectifDtos;
    }

    @Override
    public String toString() {
        return "CategoriePersonne [" +
                "idCategorie=" + idCategorie +
                ", libelle='" + libelle + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ']';
    }
}
