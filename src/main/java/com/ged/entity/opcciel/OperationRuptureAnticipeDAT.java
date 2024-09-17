package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationRuptureAnticipeDAT", schema = "Operation")*/
public class OperationRuptureAnticipeDAT extends Operation {
    private Double montantRemb;
    private Double interet;
    private Double interetRupture;
    private String codeBanqueA;
    private String numCompteA;

    public OperationRuptureAnticipeDAT() {
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

    public Double getInteretRupture() {
        return interetRupture;
    }

    public void setInteretRupture(Double interetRupture) {
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
