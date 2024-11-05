package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_OperationCommission", schema = "Operation")
public class OperationCommission extends Operation {
    private LocalDateTime dateSolde;
    @Column(precision = 18, scale = 6)
    private BigDecimal montantCommission;
    private String typeCommission;

    public OperationCommission() {
    }

    public LocalDateTime getDateSolde() {
        return dateSolde;
    }

    public void setDateSolde(LocalDateTime dateSolde) {
        this.dateSolde = dateSolde;
    }

    public BigDecimal getMontantCommission() {
        return montantCommission;
    }

    public void setMontantCommission(BigDecimal montantCommission) {
        this.montantCommission = montantCommission;
    }

    public String getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(String typeCommission) {
        this.typeCommission = typeCommission;
    }
}
