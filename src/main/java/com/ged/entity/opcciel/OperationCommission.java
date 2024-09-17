package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;

import java.time.LocalDateTime;

/*@Entity
@Table(name = "T_OperationCommission", schema = "Operation")*/
public class OperationCommission extends Operation {
    private LocalDateTime dateSolde;
    private Double montantCommission;
    private String typeCommission;

    public OperationCommission() {
    }

    public LocalDateTime getDateSolde() {
        return dateSolde;
    }

    public void setDateSolde(LocalDateTime dateSolde) {
        this.dateSolde = dateSolde;
    }

    public Double getMontantCommission() {
        return montantCommission;
    }

    public void setMontantCommission(Double montantCommission) {
        this.montantCommission = montantCommission;
    }

    public String getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(String typeCommission) {
        this.typeCommission = typeCommission;
    }
}
