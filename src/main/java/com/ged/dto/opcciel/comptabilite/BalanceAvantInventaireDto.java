package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.PlanDto;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceAvantInventaireDto {
	private Long idBalanceAvantInventaire;
	private Long idOcc;
	private OpcvmDto opcvm;
	private String numCompteComptable;
	private LocalDateTime dateBalance;
	private PlanDto plan;
	private Double debutD;
	private Double debutC;
	private Double mvtPeriodeD;
	private Double mvtPeriodeC;
	private Double debit;
	private Double credit;

	public BalanceAvantInventaireDto() {
	}

	public Long getIdBalanceAvantInventaire() {
		return idBalanceAvantInventaire;
	}

	public void setIdBalanceAvantInventaire(Long idBalanceAvantInventaire) {
		this.idBalanceAvantInventaire = idBalanceAvantInventaire;
	}

	public Long getIdOcc() {
		return idOcc;
	}

	public void setIdOcc(Long idOcc) {
		this.idOcc = idOcc;
	}

	public OpcvmDto getOpcvm() {
		return opcvm;
	}

	public void setOpcvm(OpcvmDto opcvm) {
		this.opcvm = opcvm;
	}

	public String getNumCompteComptable() {
		return numCompteComptable;
	}

	public void setNumCompteComptable(String numCompteComptable) {
		this.numCompteComptable = numCompteComptable;
	}

	public LocalDateTime getDateBalance() {
		return dateBalance;
	}

	public void setDateBalance(LocalDateTime dateBalance) {
		this.dateBalance = dateBalance;
	}

	public PlanDto getPlan() {
		return plan;
	}

	public void setPlan(PlanDto plan) {
		this.plan = plan;
	}

	public Double getDebutD() {
		return debutD;
	}

	public void setDebutD(Double debutD) {
		this.debutD = debutD;
	}

	public Double getDebutC() {
		return debutC;
	}

	public void setDebutC(Double debutC) {
		this.debutC = debutC;
	}

	public Double getMvtPeriodeD() {
		return mvtPeriodeD;
	}

	public void setMvtPeriodeD(Double mvtPeriodeD) {
		this.mvtPeriodeD = mvtPeriodeD;
	}

	public Double getMvtPeriodeC() {
		return mvtPeriodeC;
	}

	public void setMvtPeriodeC(Double mvtPeriodeC) {
		this.mvtPeriodeC = mvtPeriodeC;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}
}
