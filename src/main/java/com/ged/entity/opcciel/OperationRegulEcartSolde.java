package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "T_OperationRegulEcartSolde", schema = "Operation")
public class OperationRegulEcartSolde extends Operation {
    @Column(precision = 18, scale = 6)
    private BigDecimal soldeEspeceDepositaire;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationRegulEcartSolde() {
    }

    public BigDecimal getSoldeEspeceDepositaire() {
        return soldeEspeceDepositaire;
    }

    public void setSoldeEspeceDepositaire(BigDecimal soldeEspeceDepositaire) {
        this.soldeEspeceDepositaire = soldeEspeceDepositaire;
    }
//    public Boolean getSupprimer() {
//        return supprimer;
//    }

//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

}
