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
public class ReleveDePartActionnaireRequest {
    private Long idActionnaire;
    private String actionnaire;
    private Long idOpcvm;
    private String type;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private DatatableParameters datatableParameters;
    private String Descrip;

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public String getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(String actionnaire) {
        this.actionnaire = actionnaire;
    }

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

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }
}
