package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanDto {
    private String codePlan;
	private String libellePlan;
    private String numCompteCapital;
    private String numCompteBenefice;
    private String numComptePerte;
    private String numCompteResInsDistribution;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private String userLogin;
    private Long numLigne;
    private Boolean supprimer;
    private byte[] rowvers;
    public PlanDto() {
    }

    public String getCodePlan() {
        return codePlan;
    }

    public void setCodePlan(String codePlan) {
        this.codePlan = codePlan;
    }

    public String getLibellePlan() {
        return libellePlan;
    }

    public void setLibellePlan(String libellePlan) {
        this.libellePlan = libellePlan;
    }

    public String getNumCompteCapital() {
        return numCompteCapital;
    }

    public void setNumCompteCapital(String numCompteCapital) {
        this.numCompteCapital = numCompteCapital;
    }

    public String getNumCompteBenefice() {
        return numCompteBenefice;
    }

    public void setNumCompteBenefice(String numCompteBenefice) {
        this.numCompteBenefice = numCompteBenefice;
    }

    public String getNumComptePerte() {
        return numComptePerte;
    }

    public void setNumComptePerte(String numComptePerte) {
        this.numComptePerte = numComptePerte;
    }

    public String getNumCompteResInsDistribution() {
        return numCompteResInsDistribution;
    }

    public void setNumCompteResInsDistribution(String numCompteResInsDistribution) {
        this.numCompteResInsDistribution = numCompteResInsDistribution;
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
