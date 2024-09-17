package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_Plan", schema = "Comptabilite")
public class Plan extends Base {
    @Id
    @Column(name = "codePlan")
    private String codePlan;
	private String libellePlan;
    private String numCompteCapital;
    private String numCompteBenefice;
    private String numComptePerte;
    private String numCompteResInsDistribution;

    public Plan() {
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
}
