package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@DiscriminatorValue("RES_RLQ")
@Table(name = "T_OperationRestitutionReliquat", schema = "Operation")
public class OperationRestitutionReliquat extends Operation {
    private Boolean estGenere;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationRestitutionReliquat() {
        super();
    }

//    public Boolean getSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

    public Boolean getEstGenere() {
        return estGenere;
    }

    public void setEstGenere(Boolean estGenere) {
        this.estGenere = estGenere;
    }
}
