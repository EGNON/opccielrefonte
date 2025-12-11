package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EtatFinancierTrimestrielMontantFraisGestionRequest {
    private Long idOpcvm;
    private String type;
    private LocalDateTime dateEstimation;
    private DatatableParameters datatableParameters;
    private String letterDate;
    private String periodicite;
    private String denominationOpcvm;
    private String raisonSocial;
    private String format;

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateEstimation() {
        return dateEstimation;
    }

    public void setDateEstimation(LocalDateTime dateEstimation) {
        this.dateEstimation = dateEstimation;
    }

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }

    public String getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(String letterDate) {
        this.letterDate = letterDate;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getDenominationOpcvm() {
        return denominationOpcvm;
    }

    public void setDenominationOpcvm(String denominationOpcvm) {
        this.denominationOpcvm = denominationOpcvm;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
