package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_OperationPaiementCharge", schema = "Operation")
public class OperationPaiementCharge extends Operation {
    private String codeCharge;
    private Boolean estGenere;
    private BigDecimal montantCharge;

    private Boolean estPayee;
    private Long idSeancePaiement;
    private LocalDateTime dateSolde;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationPaiementCharge() {
    }

    public LocalDateTime getDateSolde() {
        return dateSolde;
    }

    public void setDateSolde(LocalDateTime dateSolde) {
        this.dateSolde = dateSolde;
    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }

    public Boolean getEstGenere() {
        return estGenere;
    }

    public void setEstGenere(Boolean estGenere) {
        this.estGenere = estGenere;
    }

    public BigDecimal getMontantCharge() {
        return montantCharge;
    }

    public void setMontantCharge(BigDecimal montantCharge) {
        this.montantCharge = montantCharge;
    }
    public Boolean getEstPayee() {
        return estPayee;
    }

    public void setEstPayee(Boolean estPayee) {
        this.estPayee = estPayee;
    }

    public Long getIdSeancePaiement() {
        return idSeancePaiement;
    }

    public void setIdSeancePaiement(Long idSeancePaiement) {
        this.idSeancePaiement = idSeancePaiement;
    }
}
