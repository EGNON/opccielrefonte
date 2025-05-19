package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "T_OperationDeclassementResultat", schema = "Operation")
public class OperationDeclassementResultat extends Operation {
    @Column(precision = 18, scale = 6)
    private BigDecimal ranBeneficiaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal ranDeficitaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal benefice;
    @Column(precision = 18, scale = 6)
    private BigDecimal perte;
    @Column(precision = 18, scale = 6)
    private BigDecimal beneficeInstanceAffec;
    @Column(precision = 18, scale = 6)
    private BigDecimal perteInstanceAffec;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationDeclassementResultat() {
    }

//    public Boolean getSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

    public BigDecimal getRanBeneficiaire() {
        return ranBeneficiaire;
    }

    public void setRanBeneficiaire(BigDecimal ranBeneficiaire) {
        this.ranBeneficiaire = ranBeneficiaire;
    }

    public BigDecimal getRanDeficitaire() {
        return ranDeficitaire;
    }

    public void setRanDeficitaire(BigDecimal ranDeficitaire) {
        this.ranDeficitaire = ranDeficitaire;
    }

    public BigDecimal getBenefice() {
        return benefice;
    }

    public void setBenefice(BigDecimal benefice) {
        this.benefice = benefice;
    }

    public BigDecimal getPerte() {
        return perte;
    }

    public void setPerte(BigDecimal perte) {
        this.perte = perte;
    }

    public BigDecimal getBeneficeInstanceAffec() {
        return beneficeInstanceAffec;
    }

    public void setBeneficeInstanceAffec(BigDecimal beneficeInstanceAffec) {
        this.beneficeInstanceAffec = beneficeInstanceAffec;
    }

    public BigDecimal getPerteInstanceAffec() {
        return perteInstanceAffec;
    }

    public void setPerteInstanceAffec(BigDecimal perteInstanceAffec) {
        this.perteInstanceAffec = perteInstanceAffec;
    }
}
