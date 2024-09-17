package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_BalanceAvantInventaire", schema = "Comptabilite")
public class BalanceAvantInventaire extends Base {
    @Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBalanceAvantInventaire;
	private Long idOcc;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idOpcvm")
	private Opcvm opcvm;
	private String numCompteComptable;
	private LocalDateTime dateBalance;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codePlan")
	private Plan plan;
	private Double debutD;
	private Double debutC;
	private Double mvtPeriodeD;
	private Double mvtPeriodeC;
	private Double debit;
	private Double credit;

	public BalanceAvantInventaire() {
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

	public Opcvm getOpcvm() {
		return opcvm;
	}

	public void setOpcvm(Opcvm opcvm) {
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

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
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
