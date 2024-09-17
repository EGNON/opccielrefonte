package com.ged.entity.titresciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationSplitReverseSplit", schema = "OperationCapital")*/
public class OperationSplitReverseSplit extends Operation {
    private int pariteDebut;
    private int pariteFin;
    private Double qteActuelle;
    private Double qte_ApresOperation;

    public OperationSplitReverseSplit() {
    }

    public int getPariteDebut() {
        return pariteDebut;
    }

    public void setPariteDebut(int pariteDebut) {
        this.pariteDebut = pariteDebut;
    }

    public int getPariteFin() {
        return pariteFin;
    }

    public void setPariteFin(int pariteFin) {
        this.pariteFin = pariteFin;
    }

    public Double getQteActuelle() {
        return qteActuelle;
    }

    public void setQteActuelle(Double qteActuelle) {
        this.qteActuelle = qteActuelle;
    }

    public Double getQte_ApresOperation() {
        return qte_ApresOperation;
    }

    public void setQte_ApresOperation(Double qte_ApresOperation) {
        this.qte_ApresOperation = qte_ApresOperation;
    }
}
