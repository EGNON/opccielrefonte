package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "T_OperationDetachementDAT", schema = "Operation")
public class OperationDetachementDAT extends Operation {
    @Column(precision = 18, scale = 6)
    private BigDecimal montantRemb;
    @Column(precision = 18, scale = 6)
    private BigDecimal interet;
    private Boolean estPaye;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationDetachementDAT() {
    }

//    public Boolean getSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

    public BigDecimal getMontantRemb() {
        return montantRemb;
    }

    public void setMontantRemb(BigDecimal montantRemb) {
        this.montantRemb = montantRemb;
    }

    public BigDecimal getInteret() {
        return interet;
    }

    public void setInteret(BigDecimal interet) {
        this.interet = interet;
    }

    public Boolean getEstPaye() {
        return estPaye;
    }

    public void setEstPaye(Boolean estPaye) {
        this.estPaye = estPaye;
    }
}
