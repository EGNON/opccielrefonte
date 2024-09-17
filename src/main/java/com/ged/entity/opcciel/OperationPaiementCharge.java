package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationPaiementCharge", schema = "Operation")*/
public class OperationPaiementCharge extends Operation {
    private String codeCharge;
    private boolean estGenere;

    public OperationPaiementCharge() {
    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }

    public boolean isEstGenere() {
        return estGenere;
    }

    public void setEstGenere(boolean estGenere) {
        this.estGenere = estGenere;
    }
}
