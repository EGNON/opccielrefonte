package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.CategoriePersonneDto;
import com.ged.dto.standard.PeriodiciteDto;
import com.ged.dto.standard.PersonnelDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectifReelDto {
    private long idObjReel;
    private float valeurReelleAtteinte = 0;
    @CreationTimestamp
    private LocalDateTime dateCreationServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifClient;
    private LocalDateTime rowvers;
    private long numLigne;
    private boolean supprimer;
    private String userLogin;
    private PersonnelDto personnelDto;
    private IndicateurDto indicateurDto;
    private PeriodiciteDto periodiciteDto;
    private CategoriePersonneDto categoriePersonne;

    public ObjectifReelDto(){

    }

    public ObjectifReelDto(float valeurReelleAtteinte, String userLogin, ObjectifAffecteDto objectifAffecteDto) {
        this.valeurReelleAtteinte = valeurReelleAtteinte;
        this.userLogin = userLogin;

    }

    public long getIdObjReel() {
        return idObjReel;
    }

    public void setIdObjReel(long idObjReel) {
        this.idObjReel = idObjReel;
    }

    public float getValeurReelleAtteinte() {
        return valeurReelleAtteinte;
    }

    public void setValeurReelleAtteinte(float valeurReelleAtteinte) {
        this.valeurReelleAtteinte = valeurReelleAtteinte;
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

    public LocalDateTime getRowvers() {
        return rowvers;
    }

    public void setRowvers(LocalDateTime rowvers) {
        this.rowvers = rowvers;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public PersonnelDto getPersonnelDto() {
        return personnelDto;
    }

    public void setPersonnelDto(PersonnelDto personnelDto) {
        this.personnelDto = personnelDto;
    }

    public IndicateurDto getIndicateurDto() {
        return indicateurDto;
    }

    public void setIndicateurDto(IndicateurDto indicateurDto) {
        this.indicateurDto = indicateurDto;
    }

    public PeriodiciteDto getPeriodiciteDto() {
        return periodiciteDto;
    }

    public void setPeriodiciteDto(PeriodiciteDto periodiciteDto) {
        this.periodiciteDto = periodiciteDto;
    }

    public CategoriePersonneDto getCategoriePersonneDto() {
        return categoriePersonne;
    }

    public void setCategoriePersonneDto(CategoriePersonneDto categoriePersonne) {
        this.categoriePersonne = categoriePersonne;
    }
}
