package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationCommissionDto extends OperationDto {
    private LocalDateTime dateSolde;
    private BigDecimal montantCommission;
    private String typeCommission;

    public OperationCommissionDto() {
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
