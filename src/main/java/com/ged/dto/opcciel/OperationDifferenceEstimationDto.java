package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.titresciel.TitreDto;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDifferenceEstimationDto {
	private Long idSeance;
	private TitreDto titre;
	private OpcvmDto opcvm;
	private Double qteDetenue;
	private Double cours;
	private Double cumpT;
	private Double cumpReel;
	private Double plusOuMoinsValue;
	private Double nbreJourCourus;
	private Double interetCourus;
	private Double valeurVDECours;
	private Double valeurVDEInteret;
	private Double idOpCours;
	private Double idOpInteret;
	private Double irvm;
	private boolean estVerifie;
	private LocalDateTime dateVerification;
	private String userLoginVerificateur;
	private boolean estVerifie1;
	private LocalDateTime dateVerification1;
	private String userLoginVerificateur1;
	private boolean estVerifie2;
	private LocalDateTime dateVerification2;
	private String userLoginVerificateur2;

	public OperationDifferenceEstimationDto() {
	}

	public Long getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(Long idSeance) {
		this.idSeance = idSeance;
	}

	public TitreDto getTitre() {
		return titre;
	}

	public void setTitre(TitreDto titre) {
		this.titre = titre;
	}

	public OpcvmDto getOpcvm() {
		return opcvm;
	}

	public void setOpcvm(OpcvmDto opcvm) {
		this.opcvm = opcvm;
	}

	public Double getQteDetenue() {
		return qteDetenue;
	}

	public void setQteDetenue(Double qteDetenue) {
		this.qteDetenue = qteDetenue;
	}

	public Double getCours() {
		return cours;
	}

	public void setCours(Double cours) {
		this.cours = cours;
	}

	public Double getCumpT() {
		return cumpT;
	}

	public void setCumpT(Double cumpT) {
		this.cumpT = cumpT;
	}

	public Double getCumpReel() {
		return cumpReel;
	}

	public void setCumpReel(Double cumpReel) {
		this.cumpReel = cumpReel;
	}

	public Double getPlusOuMoinsValue() {
		return plusOuMoinsValue;
	}

	public void setPlusOuMoinsValue(Double plusOuMoinsValue) {
		this.plusOuMoinsValue = plusOuMoinsValue;
	}

	public Double getNbreJourCourus() {
		return nbreJourCourus;
	}

	public void setNbreJourCourus(Double nbreJourCourus) {
		this.nbreJourCourus = nbreJourCourus;
	}

	public Double getInteretCourus() {
		return interetCourus;
	}

	public void setInteretCourus(Double interetCourus) {
		this.interetCourus = interetCourus;
	}

	public Double getValeurVDECours() {
		return valeurVDECours;
	}

	public void setValeurVDECours(Double valeurVDECours) {
		this.valeurVDECours = valeurVDECours;
	}

	public Double getValeurVDEInteret() {
		return valeurVDEInteret;
	}

	public void setValeurVDEInteret(Double valeurVDEInteret) {
		this.valeurVDEInteret = valeurVDEInteret;
	}

	public Double getIdOpCours() {
		return idOpCours;
	}

	public void setIdOpCours(Double idOpCours) {
		this.idOpCours = idOpCours;
	}

	public Double getIdOpInteret() {
		return idOpInteret;
	}

	public void setIdOpInteret(Double idOpInteret) {
		this.idOpInteret = idOpInteret;
	}

	public Double getIrvm() {
		return irvm;
	}

	public void setIrvm(Double irvm) {
		this.irvm = irvm;
	}

	public boolean isEstVerifie() {
		return estVerifie;
	}

	public void setEstVerifie(boolean estVerifie) {
		this.estVerifie = estVerifie;
	}

	public LocalDateTime getDateVerification() {
		return dateVerification;
	}

	public void setDateVerification(LocalDateTime dateVerification) {
		this.dateVerification = dateVerification;
	}

	public String getUserLoginVerificateur() {
		return userLoginVerificateur;
	}

	public void setUserLoginVerificateur(String userLoginVerificateur) {
		this.userLoginVerificateur = userLoginVerificateur;
	}

	public boolean isEstVerifie1() {
		return estVerifie1;
	}

	public void setEstVerifie1(boolean estVerifie1) {
		this.estVerifie1 = estVerifie1;
	}

	public LocalDateTime getDateVerification1() {
		return dateVerification1;
	}

	public void setDateVerification1(LocalDateTime dateVerification1) {
		this.dateVerification1 = dateVerification1;
	}

	public String getUserLoginVerificateur1() {
		return userLoginVerificateur1;
	}

	public void setUserLoginVerificateur1(String userLoginVerificateur1) {
		this.userLoginVerificateur1 = userLoginVerificateur1;
	}

	public boolean isEstVerifie2() {
		return estVerifie2;
	}

	public void setEstVerifie2(boolean estVerifie2) {
		this.estVerifie2 = estVerifie2;
	}

	public LocalDateTime getDateVerification2() {
		return dateVerification2;
	}

	public void setDateVerification2(LocalDateTime dateVerification2) {
		this.dateVerification2 = dateVerification2;
	}

	public String getUserLoginVerificateur2() {
		return userLoginVerificateur2;
	}

	public void setUserLoginVerificateur2(String userLoginVerificateur2) {
		this.userLoginVerificateur2 = userLoginVerificateur2;
	}
}
