package com.ged.entity.titresciel;

import com.ged.entity.opcciel.comptabilite.Operation;

/*@Entity
@Table(name = "T_OperationPayementRompu", schema = "OperationCapital")*/
public class OperationPayementRompu extends Operation {
    private Double nombreRompu;
    private Double coutDeSession;
    private Double montantSession;

    public OperationPayementRompu() {
    }

    public Double getNombreRompu() {
        return nombreRompu;
    }

    public void setNombreRompu(Double nombreRompu) {
        this.nombreRompu = nombreRompu;
    }

    public Double getCoutDeSession() {
        return coutDeSession;
    }

    public void setCoutDeSession(Double coutDeSession) {
        this.coutDeSession = coutDeSession;
    }

    public Double getMontantSession() {
        return montantSession;
    }

    public void setMontantSession(Double montantSession) {
        this.montantSession = montantSession;
    }
}
