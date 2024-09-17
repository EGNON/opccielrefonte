package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*@Entity
@Table(name = "T_OperationPaiementDAT", schema = "Operation")*/
public class OperationPaiementDAT extends Operation {
    private Double montantRemb;
    private Double interet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDetachementDAT",referencedColumnName = "idOperation")
    private OperationDetachementDAT operationDetachementDAT;
    private Double irc;
    private String codeBanqueA;
    private String numCompteA;
    private Double interetMoratoire;

    public OperationPaiementDAT() {
    }

    public Double getMontantRemb() {
        return montantRemb;
    }

    public void setMontantRemb(Double montantRemb) {
        this.montantRemb = montantRemb;
    }

    public Double getInteret() {
        return interet;
    }

    public void setInteret(Double interet) {
        this.interet = interet;
    }

    public OperationDetachementDAT getOperationDetachementDAT() {
        return operationDetachementDAT;
    }

    public void setOperationDetachementDAT(OperationDetachementDAT operationDetachementDAT) {
        this.operationDetachementDAT = operationDetachementDAT;
    }

    public Double getIrc() {
        return irc;
    }

    public void setIrc(Double irc) {
        this.irc = irc;
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

    public Double getInteretMoratoire() {
        return interetMoratoire;
    }

    public void setInteretMoratoire(Double interetMoratoire) {
        this.interetMoratoire = interetMoratoire;
    }
}
