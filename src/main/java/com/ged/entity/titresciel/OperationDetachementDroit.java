package com.ged.entity.titresciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationDetachementDroit", schema = "OperationCapital")*/
public class OperationDetachementDroit extends Operation {
    private Double qteAction;
    private Double qteDroit;

    public OperationDetachementDroit() {
    }

    public Double getQteAction() {
        return qteAction;
    }

    public void setQteAction(Double qteAction) {
        this.qteAction = qteAction;
    }

    public Double getQteDroit() {
        return qteDroit;
    }

    public void setQteDroit(Double qteDroit) {
        this.qteDroit = qteDroit;
    }
}
