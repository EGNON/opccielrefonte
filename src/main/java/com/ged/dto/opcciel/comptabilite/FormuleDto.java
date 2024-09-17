package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormuleDto {
    private Long idFormule;
    private Long idOcc;
	private String libelleFormule;
	private TypeFormuleDto typeFormule;
	private boolean estSysteme;
	private LocalDateTime dateCreationServeur;
	private LocalDateTime dateDernModifServeur;
	private LocalDateTime dateDernModifClient;
	private String userLogin;
	private Long numLigne;
	private Boolean supprimer;
	private byte[] rowvers;
	private Set<ModeleEcritureFormuleDto> modeleEcritureFormules;
	public FormuleDto() {
	}

	public Set<ModeleEcritureFormuleDto> getModeleEcritureFormules() {
		return modeleEcritureFormules;
	}

	public void setModeleEcritureFormules(Set<ModeleEcritureFormuleDto> modeleEcritureFormules) {
		this.modeleEcritureFormules = modeleEcritureFormules;
	}

	public Long getIdOcc() {
		return idOcc;
	}

	public void setIdOcc(Long idOcc) {
		this.idOcc = idOcc;
	}

	public Long getIdFormule() {
		return idFormule;
	}

	public void setIdFormule(Long idFormule) {
		this.idFormule = idFormule;
	}

	public String getLibelleFormule() {
		return libelleFormule;
	}

	public void setLibelleFormule(String libelleFormule) {
		this.libelleFormule = libelleFormule;
	}

	public TypeFormuleDto getTypeFormule() {
		return typeFormule;
	}

	public void setTypeFormule(TypeFormuleDto typeFormule) {
		this.typeFormule = typeFormule;
	}

	public boolean isEstSysteme() {
		return estSysteme;
	}

	public void setEstSysteme(boolean estSysteme) {
		this.estSysteme = estSysteme;
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
