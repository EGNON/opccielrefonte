package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrecalculSouscriptionRequest {
    private Long idSeance;
    private Long idOpcvm;
    private Long idPersonne;
    private DatatableParameters datatableParameters;

    public PrecalculSouscriptionRequest() {
    }

    public PrecalculSouscriptionRequest(Long idSeance, Long idOpcvm, Long idPersonne) {
        this.idSeance = idSeance;
        this.idOpcvm = idOpcvm;
        this.idPersonne = idPersonne;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }
}
