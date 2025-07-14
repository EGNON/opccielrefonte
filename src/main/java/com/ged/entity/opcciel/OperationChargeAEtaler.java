package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "T_OperationChargeAEtaler", schema = "Operation")
public class OperationChargeAEtaler extends Operation {
    private boolean estGenere;
    private BigDecimal actifBrut;
    private String codeCharge;
    private int nbreJour;
    private int usance;
    private String codeModele;

//    @ColumnDefault("0")
//    private Boolean supprimer = false;

    public OperationChargeAEtaler() {
    }

//    public Boolean getSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

    public boolean isEstGenere() {
        return estGenere;
    }

    public void setEstGenere(boolean estGenere) {
        this.estGenere = estGenere;
    }

    public BigDecimal getActifBrut() {
        return actifBrut;
    }

    public void setActifBrut(BigDecimal actifBrut) {
        this.actifBrut = actifBrut;
    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }

    public int getNbreJour() {
        return nbreJour;
    }

    public void setNbreJour(int nbreJour) {
        this.nbreJour = nbreJour;
    }

    public int getUsance() {
        return usance;
    }

    public void setUsance(int usance) {
        this.usance = usance;
    }

    public String getCodeModele() {
        return codeModele;
    }

    public void setCodeModele(String codeModele) {
        this.codeModele = codeModele;
    }
}
