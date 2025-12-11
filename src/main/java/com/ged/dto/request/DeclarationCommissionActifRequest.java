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
public class DeclarationCommissionActifRequest {
    private Long idOpcvm;
    private String trimestre;
    private Long anneeExo;
    private BigDecimal taux;
    private String type;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private DatatableParameters datatableParameters;
    private String Descrip;

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public Long getAnneeExo() {
        return anneeExo;
    }

    public void setAnneeExo(Long anneeExo) {
        this.anneeExo = anneeExo;
    }

    public BigDecimal getTaux() {
        return taux;
    }

    public void setTaux(BigDecimal taux) {
        this.taux = taux;
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
