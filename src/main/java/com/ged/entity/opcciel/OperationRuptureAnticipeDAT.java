package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "T_OperationRuptureAnticipeDAT", schema = "Operation")
public class OperationRuptureAnticipeDAT extends Operation {
    @Column(precision = 18, scale = 6)
    private BigDecimal montantRemb;
    @Column(precision = 18, scale = 6)
    private BigDecimal interet;
    @Column(precision = 18, scale = 6)
    private BigDecimal interetRupture;
    private String codeBanqueA;
    private String numCompteA;

    public OperationRuptureAnticipeDAT() {
    }

    public BigDecimal getMontantRemb() {
        return montantRemb;
    }

    public void setMontantRemb(BigDecimal montantRemb) {
        this.montantRemb = montantRemb;
    }

    public BigDecimal getInteret() {
        return interet;
    }

    public void setInteret(BigDecimal interet) {
        this.interet = interet;
    }

    public BigDecimal getInteretRupture() {
        return interetRupture;
    }

    public void setInteretRupture(BigDecimal interetRupture) {
        this.interetRupture = interetRupture;
    }

    public String getCodeBanqueA() {
        return codeBanqueA;
    }

    public void setCodeBanqueA(String codeBanqueA) {
        this.codeBanqueA = codeBanqueA;
    }

    public String getNumCompteA() {
        return numCompteA;
    }

    public void setNumCompteA(String numCompteA) {
        this.numCompteA = numCompteA;
    }
}
