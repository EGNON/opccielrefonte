package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_OperationConstatationCharge", schema = "Operation")
public class OperationConstatationCharge extends Operation {
    private LocalDateTime dateSolde;
    @Column(precision = 18, scale = 6)
    private BigDecimal montantCharge;
    private String codeCharge;
    private boolean estPayee;
    private Long idSeancePaiement;

    public OperationConstatationCharge() {
    }

    public LocalDateTime getDateSolde() {
        return dateSolde;
    }

    public void setDateSolde(LocalDateTime dateSolde) {
        this.dateSolde = dateSolde;
    }

    public BigDecimal getMontantCharge() {
        return montantCharge;
    }

    public void setMontantCharge(BigDecimal montantCharge) {
        this.montantCharge = montantCharge;
    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }

    public boolean isEstPayee() {
        return estPayee;
    }

    public void setEstPayee(boolean estPayee) {
        this.estPayee = estPayee;
    }

    public Long getIdSeancePaiement() {
        return idSeancePaiement;
    }

    public void setIdSeancePaiement(Long idSeancePaiement) {
        this.idSeancePaiement = idSeancePaiement;
    }
}
