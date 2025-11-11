package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortefeuilleActionnaireRequest {
    private String idActionnaire;
    private Long idOpcvm;
    private LocalDateTime dateDebutEstimation;
    private LocalDateTime dateEstimation;
    private DatatableParameters datatableParameters;

    public String getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(String idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public LocalDateTime getDateDebutEstimation() {
        return dateDebutEstimation;
    }

    public void setDateDebutEstimation(LocalDateTime dateDebutEstimation) {
        this.dateDebutEstimation = dateDebutEstimation;
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
}
