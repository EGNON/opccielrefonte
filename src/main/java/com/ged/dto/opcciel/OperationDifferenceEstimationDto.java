package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.titresciel.TitreDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDifferenceEstimationDto {
	private Long idSeance;
	private TitreDto titre;
	private OpcvmDto opcvm;
	private BigDecimal qteDetenue;
	private BigDecimal cours;
	private BigDecimal cumpT;
	private BigDecimal cumpReel;
	private BigDecimal plusOuMoinsValue;
	private BigDecimal nbreJourCourus;
	private BigDecimal interetCourus;
	private BigDecimal valeurVDECours;
	private BigDecimal valeurVDEInteret;
	private BigDecimal idOpCours;
	private BigDecimal idOpInteret;
	private BigDecimal irvm;
	private boolean estVerifie;
	private LocalDateTime dateVerification;
	private LocalDateTime dateValeur;
	private String userLoginVerificateur;
	private String userLogin;
	private boolean estVerifie1;
	private LocalDateTime dateVerification1;
	private String userLoginVerificateur1;
	private boolean estVerifie2;
	private LocalDateTime dateVerification2;
	private String userLoginVerificateur2;

	public OperationDifferenceEstimationDto() {
	}

	public LocalDateTime getDateValeur() {
		return dateValeur;
	}

	public void setDateValeur(LocalDateTime dateValeur) {
		this.dateValeur = dateValeur;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
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

	public BigDecimal getQteDetenue() {
		return qteDetenue;
	}

	public void setQteDetenue(BigDecimal qteDetenue) {
		this.qteDetenue = qteDetenue;
	}

	public BigDecimal getCours() {
		return cours;
	}

	public void setCours(BigDecimal cours) {
		this.cours = cours;
	}

	public BigDecimal getCumpT() {
		return cumpT;
	}

	public void setCumpT(BigDecimal cumpT) {
		this.cumpT = cumpT;
	}

	public BigDecimal getCumpReel() {
		return cumpReel;
	}

	public void setCumpReel(BigDecimal cumpReel) {
		this.cumpReel = cumpReel;
	}

	public BigDecimal getPlusOuMoinsValue() {
		return plusOuMoinsValue;
	}

	public void setPlusOuMoinsValue(BigDecimal plusOuMoinsValue) {
		this.plusOuMoinsValue = plusOuMoinsValue;
	}

	public BigDecimal getNbreJourCourus() {
		return nbreJourCourus;
	}

	public void setNbreJourCourus(BigDecimal nbreJourCourus) {
		this.nbreJourCourus = nbreJourCourus;
	}

	public BigDecimal getInteretCourus() {
		return interetCourus;
	}

	public void setInteretCourus(BigDecimal interetCourus) {
		this.interetCourus = interetCourus;
	}

	public BigDecimal getValeurVDECours() {
		return valeurVDECours;
	}

	public void setValeurVDECours(BigDecimal valeurVDECours) {
		this.valeurVDECours = valeurVDECours;
	}

	public BigDecimal getValeurVDEInteret() {
		return valeurVDEInteret;
	}

	public void setValeurVDEInteret(BigDecimal valeurVDEInteret) {
		this.valeurVDEInteret = valeurVDEInteret;
	}

	public BigDecimal getIdOpCours() {
		return idOpCours;
	}

	public void setIdOpCours(BigDecimal idOpCours) {
		this.idOpCours = idOpCours;
	}

	public BigDecimal getIdOpInteret() {
		return idOpInteret;
	}

	public void setIdOpInteret(BigDecimal idOpInteret) {
		this.idOpInteret = idOpInteret;
	}

	public BigDecimal getIrvm() {
		return irvm;
	}

	public void setIrvm(BigDecimal irvm) {
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
