package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationRestitutionReliquat", schema = "Operation")*/
public class OperationRestitutionReliquat extends Operation {
    private boolean estGenere;

    public OperationRestitutionReliquat() {
    }

    public boolean isEstGenere() {
        return estGenere;
    }

    public void setEstGenere(boolean estGenere) {
        this.estGenere = estGenere;
    }
}
