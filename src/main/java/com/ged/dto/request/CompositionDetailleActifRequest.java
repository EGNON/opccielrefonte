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
public class CompositionDetailleActifRequest {
    private Long idOpcvm;
    private LocalDateTime dateEstimation;
    private LocalDateTime dateOperation;
    private DatatableParameters datatableParameters;
    private String letterDate;
    private String frequence;
    private String sigleOpcvm;
    private String denominationOpcvm;

    public CompositionDetailleActifRequest(LocalDateTime dateEstimation, LocalDateTime dateOperation, DatatableParameters datatableParameters, String letterDate, String frequence, String sigleOpcvm, String denominationOpcvm) {
        this.dateEstimation = dateEstimation;
        this.dateOperation = dateOperation;
        this.datatableParameters = datatableParameters;
        this.letterDate = letterDate;
        this.frequence = frequence;
        this.sigleOpcvm = sigleOpcvm;
        this.denominationOpcvm = denominationOpcvm;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public LocalDateTime getDateEstimation() {
        return dateEstimation;
    }

    public void setDateEstimation(LocalDateTime dateEstimation) {
        this.dateEstimation = dateEstimation;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
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

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public String getSigleOpcvm() {
        return sigleOpcvm;
    }

    public void setSigleOpcvm(String sigleOpcvm) {
        this.sigleOpcvm = sigleOpcvm;
    }

    public String getDenominationOpcvm() {
        return denominationOpcvm;
    }

    public void setDenominationOpcvm(String denominationOpcvm) {
        this.denominationOpcvm = denominationOpcvm;
    }
}
