package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.PlanDto;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PosteComptableDto {
	private String codePosteComptable;
	private PlanDto plan;
	private String libellePosteComptable;
	private String type;
	private String formule;
	private LocalDateTime dateCreationServeur;
	private LocalDateTime dateDernModifServeur;
	private LocalDateTime dateDernModifClient;
	private String userLogin;
	private Long numLigne;
	private Boolean supprimer;
	private byte[] rowvers;

	public PosteComptableDto() {
	}

	public PlanDto getPlan() {
		return plan;
	}

	public void setPlan(PlanDto plan) {
		this.plan = plan;
	}

	public String getLibellePosteComptable() {
		return libellePosteComptable;
	}

	public void setLibellePosteComptable(String libellePosteComptable) {
		this.libellePosteComptable = libellePosteComptable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormule() {
		return formule;
	}

	public void setFormule(String formule) {
		this.formule = formule;
	}

	public String getCodePosteComptable() {
		return codePosteComptable;
	}

	public void setCodePosteComptable(String codePosteComptable) {
		this.codePosteComptable = codePosteComptable;
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
