package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.entity.opcciel.Charge;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationPaiementChargeDto extends OperationDto {
    private String codeCharge;
    private Boolean estGenere;
    private BigDecimal montantCharge;
    private Boolean estPayee;
    private Long idSeancePaiement;
    private LocalDateTime dateSolde;
    private String userLogin;
    public OperationPaiementChargeDto() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

    public boolean isEstGenere() {
        return estGenere;
    }

    public void setEstGenere(boolean estGenere) {
        this.estGenere = estGenere;
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
