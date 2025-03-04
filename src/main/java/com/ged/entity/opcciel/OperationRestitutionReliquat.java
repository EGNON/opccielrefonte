package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("RES_RLQ")
@Table(name = "T_OperationRestitutionReliquat", schema = "Operation")
public class OperationRestitutionReliquat extends Operation {
    private Boolean estGenere;

    public OperationRestitutionReliquat() {
        super();
    }

    public Boolean getEstGenere() {
        return estGenere;
    }

    public void setEstGenere(Boolean estGenere) {
        this.estGenere = estGenere;
    }
}
