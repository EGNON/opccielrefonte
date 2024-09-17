package com.ged.entity.titresciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationConversionTitre", schema = "OperationCapital")*/
public class OperationConversionTitre extends Operation {
    private Double nombreDroit;
    private Double qteAttribue;
    private Double nombreRompu;

    public OperationConversionTitre() {
    }

    public Double getNombreDroit() {
        return nombreDroit;
    }

    public void setNombreDroit(Double nombreDroit) {
        this.nombreDroit = nombreDroit;
    }

    public Double getQteAttribue() {
        return qteAttribue;
    }

    public void setQteAttribue(Double qteAttribue) {
        this.qteAttribue = qteAttribue;
    }

    public Double getNombreRompu() {
        return nombreRompu;
    }

    public void setNombreRompu(Double nombreRompu) {
        this.nombreRompu = nombreRompu;
    }
}
