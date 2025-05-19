package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "T_OperationPaiementRachat", schema = "Operation")
public class OperationPaiementRachat extends Operation {
    private Boolean estGenere;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationPaiementRachat() {
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
