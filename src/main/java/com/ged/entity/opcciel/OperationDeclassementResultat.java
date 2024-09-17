package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationDeclassementResultat", schema = "Operation")*/
public class OperationDeclassementResultat extends Operation {
    private Double ranBeneficiaire;
    private Double ranDeficitaire;
    private Double benefice;
    private Double perte;
    private Double beneficeInstanceAffec;
    private Double perteInstanceAffec;

    public OperationDeclassementResultat() {
    }

    public Double getRanBeneficiaire() {
        return ranBeneficiaire;
    }

    public void setRanBeneficiaire(Double ranBeneficiaire) {
        this.ranBeneficiaire = ranBeneficiaire;
    }

    public Double getRanDeficitaire() {
        return ranDeficitaire;
    }

    public void setRanDeficitaire(Double ranDeficitaire) {
        this.ranDeficitaire = ranDeficitaire;
    }

    public Double getBenefice() {
        return benefice;
    }

    public void setBenefice(Double benefice) {
        this.benefice = benefice;
    }

    public Double getPerte() {
        return perte;
    }

    public void setPerte(Double perte) {
        this.perte = perte;
    }

    public Double getBeneficeInstanceAffec() {
        return beneficeInstanceAffec;
    }

    public void setBeneficeInstanceAffec(Double beneficeInstanceAffec) {
        this.beneficeInstanceAffec = beneficeInstanceAffec;
    }

    public Double getPerteInstanceAffec() {
        return perteInstanceAffec;
    }

    public void setPerteInstanceAffec(Double perteInstanceAffec) {
        this.perteInstanceAffec = perteInstanceAffec;
    }
}
