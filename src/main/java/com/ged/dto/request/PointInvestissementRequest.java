package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data

@JsonIgnoreProperties(ignoreUnknown = true)
public class PointInvestissementRequest {
    private Long idOpcvm;
    private Boolean echue;
    private Boolean traiter;
    private Boolean detache;
    private String typeOp;
    private String type;
    private LocalDateTime dateDeb;
    private LocalDateTime dateFin;
    private LocalDateTime dateEstimation;
    private DatatableParameters datatableParameters;
    private String Descrip;
    private String denominationOpcvm;
    private LocalDateTime dateOuverture;

    public Boolean getEchue() {
        return echue;
    }

    public void setEchue(Boolean echue) {
        this.echue = echue;
    }

    public Boolean getTraiter() {
        return traiter;
    }

    public void setTraiter(Boolean traiter) {
        this.traiter = traiter;
    }

    public Boolean getDetache() {
        return detache;
    }

    public void setDetache(Boolean detache) {
        this.detache = detache;
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

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String descrip) {
        Descrip = descrip;
    }

    public String getDenominationOpcvm() {
        return denominationOpcvm;
    }

    public void setDenominationOpcvm(String denominationOpcvm) {
        this.denominationOpcvm = denominationOpcvm;
    }

    public LocalDateTime getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(LocalDateTime dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public void setTypeOp(String typeOp) {
        this.typeOp = typeOp;
    }

    public LocalDateTime getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(LocalDateTime dateDeb) {
        this.dateDeb = dateDeb;
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
