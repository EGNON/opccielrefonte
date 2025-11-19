package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EtatFinancierTrimestrielBilanTrimestrielRequest {
    private Long idOpcvm;
    private Integer annee;
    private String type;
    private LocalDateTime dateEstimation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private DatatableParameters datatableParameters;
    private String raisonSocial;
    private String format;
    private String libellePays;
    private String periodicite;
    private String denominationOpcvm;

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
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

    public String getLibellePays() {
        return libellePays;
    }

    public void setLibellePays(String libellePays) {
        this.libellePays = libellePays;
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
}
