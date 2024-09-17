package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationDetachementDAT", schema = "Operation")*/
public class OperationDetachementDAT extends Operation {
    private Double montantRemb;
    private Double interet;
    private boolean estPaye;

    public OperationDetachementDAT() {
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

    public boolean isEstPaye() {
        return estPaye;
    }

    public void setEstPaye(boolean estPaye) {
        this.estPaye = estPaye;
    }
}
