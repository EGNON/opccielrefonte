package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationPaiementRachat", schema = "Operation")*/
public class OperationPaiementRachat extends Operation {
    private boolean estGenere;

    public OperationPaiementRachat() {
    }

    public boolean isEstGenere() {
        return estGenere;
    }

    public void setEstGenere(boolean estGenere) {
        this.estGenere = estGenere;
    }
}
