package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_OperationPaiementRachat", schema = "Operation")
public class OperationPaiementRachat extends Operation {
    private Boolean estGenere;

    public OperationPaiementRachat() {
    }

    public Boolean getEstGenere() {
        return estGenere;
    }

    public void setEstGenere(Boolean estGenere) {
        this.estGenere = estGenere;
    }
}
