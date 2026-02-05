package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;
import jakarta.persistence.Convert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhaseDetachementDto extends Base {
    private BigDecimal montantDistribue;
    private BigDecimal regul;
    private BigDecimal totalADistribuer;
    private BigDecimal couponUnitaire;
    private BigDecimal nombrePartEnCirculaion;
   private BigDecimal totalDistribuer;
   private BigDecimal reste;
    private Boolean btn_Enregistrer;

    public PhaseDetachementDto() {
    }

    public BigDecimal getReste() {
        return reste;
    }

    public void setReste(BigDecimal reste) {
        this.reste = reste;
    }

    public BigDecimal getCouponUnitaire() {
        return couponUnitaire;
    }

    public void setCouponUnitaire(BigDecimal couponUnitaire) {
        this.couponUnitaire = couponUnitaire;
    }

    public BigDecimal getMontantDistribue() {
        return montantDistribue;
    }

    public void setMontantDistribue(BigDecimal montantDistribue) {
        this.montantDistribue = montantDistribue;
    }

    public BigDecimal getRegul() {
        return regul;
    }

    public void setRegul(BigDecimal regul) {
        this.regul = regul;
    }

    public BigDecimal getTotalADistribuer() {
        return totalADistribuer;
    }

    public void setTotalADistribuer(BigDecimal totalADistribuer) {
        this.totalADistribuer = totalADistribuer;
    }

    public BigDecimal getNombrePartEnCirculaion() {
        return nombrePartEnCirculaion;
    }

    public void setNombrePartEnCirculaion(BigDecimal nombrePartEnCirculaion) {
        this.nombrePartEnCirculaion = nombrePartEnCirculaion;
    }

    public BigDecimal getTotalDistribuer() {
        return totalDistribuer;
    }

    public void setTotalDistribuer(BigDecimal totalDistribuer) {
        this.totalDistribuer = totalDistribuer;
    }

    public Boolean getBtn_Enregistrer() {
        return btn_Enregistrer;
    }

    public void setBtn_Enregistrer(Boolean btn_Enregistrer) {
        this.btn_Enregistrer = btn_Enregistrer;
    }
}
