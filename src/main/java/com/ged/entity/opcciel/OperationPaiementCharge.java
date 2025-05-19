package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "T_OperationPaiementCharge", schema = "Operation")
public class OperationPaiementCharge extends Operation {
    private String codeCharge;
    private Boolean estGenere;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationPaiementCharge() {
    }

//    public Boolean getSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }

    public Boolean getEstGenere() {
        return estGenere;
    }

    public void setEstGenere(Boolean estGenere) {
        this.estGenere = estGenere;
    }
}
