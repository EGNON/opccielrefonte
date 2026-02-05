package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhaseDetachementRequest {
    private Long idOpcvm;
    private String codeExercice;
    private LocalDateTime dateSeance;
    private BigDecimal regul;
    private BigDecimal nbrePartEnCirculation;
    private BigDecimal totalADistribuer;
    private BigDecimal couponUnitaire;
    private String typeArrondi;
    private String typeForm;


    public String getTypeForm() {
        return typeForm;
    }

    public void setTypeForm(String typeForm) {
        this.typeForm = typeForm;
    }

    public BigDecimal getNbrePartEnCirculation() {
        return nbrePartEnCirculation;
    }

    public void setNbrePartEnCirculation(BigDecimal nbrePartEnCirculation) {
        this.nbrePartEnCirculation = nbrePartEnCirculation;
    }

    public BigDecimal getTotalADistribuer() {
        return totalADistribuer;
    }

    public void setTotalADistribuer(BigDecimal totalADistribuer) {
        this.totalADistribuer = totalADistribuer;
    }

    public BigDecimal getCouponUnitaire() {
        return couponUnitaire;
    }

    public void setCouponUnitaire(BigDecimal couponUnitaire) {
        this.couponUnitaire = couponUnitaire;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(String codeExercice) {
        this.codeExercice = codeExercice;
    }

    public LocalDateTime getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(LocalDateTime dateSeance) {
        this.dateSeance = dateSeance;
    }

    public BigDecimal getRegul() {
        return regul;
    }

    public void setRegul(BigDecimal regul) {
        this.regul = regul;
    }

    public String getTypeArrondi() {
        return typeArrondi;
    }

    public void setTypeArrondi(String typeArrondi) {
        this.typeArrondi = typeArrondi;
    }
}
