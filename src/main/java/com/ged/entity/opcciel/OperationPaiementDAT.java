package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "T_OperationPaiementDAT", schema = "Operation")
public class OperationPaiementDAT extends Operation {
    @Column(precision = 18, scale = 6)
    private BigDecimal montantRemb;
    @Column(precision = 18, scale = 6)
    private BigDecimal interet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDetachementDAT",referencedColumnName = "idOperation")
    private OperationDetachementDAT operationDetachementDAT;
    @Column(precision = 18, scale = 6)
    private BigDecimal irc;
    private String codeBanqueA;
    private String numCompteA;
    @Column(precision = 18, scale = 6)
    private BigDecimal interetMoratoire;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationPaiementDAT() {
    }

//    public Boolean getSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

    public OperationDetachementDAT getOperationDetachementDAT() {
        return operationDetachementDAT;
    }

    public void setOperationDetachementDAT(OperationDetachementDAT operationDetachementDAT) {
        this.operationDetachementDAT = operationDetachementDAT;
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

    public BigDecimal getIrc() {
        return irc;
    }

    public void setIrc(BigDecimal irc) {
        this.irc = irc;
    }

    public BigDecimal getInteretMoratoire() {
        return interetMoratoire;
    }

    public void setInteretMoratoire(BigDecimal interetMoratoire) {
        this.interetMoratoire = interetMoratoire;
    }
}
