package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailModeleDto {
    private String numCompteComptable;
    private int numeroOrdre;
    private ModeleEcritureDto modeleEcriture;
	private String sensMvt;
    private FormuleDto formule;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private String userLogin;
    private Long numLigne;
    private Boolean supprimer;
    private byte[] rowvers;
    private Boolean actionnaire;
    private Boolean banque;
    public DetailModeleDto() {
    }

    public Boolean getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(Boolean actionnaire) {
        this.actionnaire = actionnaire;
    }

    public Boolean getBanque() {
        return banque;
    }

    public void setBanque(Boolean banque) {
        this.banque = banque;
    }

    public String getNumCompteComptable() {
        return numCompteComptable;
    }

    public void setNumCompteComptable(String numCompteComptable) {
        this.numCompteComptable = numCompteComptable;
    }

    public int getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(int numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public ModeleEcritureDto getModeleEcriture() {
        return modeleEcriture;
    }

    public void setModeleEcriture(ModeleEcritureDto modeleEcriture) {
        this.modeleEcriture = modeleEcriture;
    }

    public String getSensMvt() {
        return sensMvt;
    }

    public void setSensMvt(String sensMvt) {
        this.sensMvt = sensMvt;
    }

    public FormuleDto getFormule() {
        return formule;
    }

    public void setFormule(FormuleDto formule) {
        this.formule = formule;
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
