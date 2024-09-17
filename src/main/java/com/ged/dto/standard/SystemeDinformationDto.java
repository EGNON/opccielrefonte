package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemeDinformationDto {
    private Long idSystemeDinformation;
    private String logiciel;
    private LocalDateTime dateAcquisition;
    private LocalDateTime dateInfoCREPMF;
    private String denomination;
    private LocalDateTime dateDebutMaintenance;
    private LocalDateTime dateFinMaintenance;
    private String principalFonctionnalite;

    public SystemeDinformationDto() {
    }

    public Long getIdSystemeDinformation() {
        return idSystemeDinformation;
    }

    public void setIdSystemeDinformation(Long idSystemeDinformation) {
        this.idSystemeDinformation = idSystemeDinformation;
    }

    public String getLogiciel() {
        return logiciel;
    }

    public void setLogiciel(String logiciel) {
        this.logiciel = logiciel;
    }

    public LocalDateTime getDateAcquisition() {
        return dateAcquisition;
    }

    public void setDateAcquisition(LocalDateTime dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }

    public LocalDateTime getDateInfoCREPMF() {
        return dateInfoCREPMF;
    }

    public void setDateInfoCREPMF(LocalDateTime dateInfoCREPMF) {
        this.dateInfoCREPMF = dateInfoCREPMF;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public LocalDateTime getDateDebutMaintenance() {
        return dateDebutMaintenance;
    }

    public void setDateDebutMaintenance(LocalDateTime dateDebutMaintenance) {
        this.dateDebutMaintenance = dateDebutMaintenance;
    }

    public LocalDateTime getDateFinMaintenance() {
        return dateFinMaintenance;
    }

    public void setDateFinMaintenance(LocalDateTime dateFinMaintenance) {
        this.dateFinMaintenance = dateFinMaintenance;
    }

    public String getPrincipalFonctionnalite() {
        return principalFonctionnalite;
    }

    public void setPrincipalFonctionnalite(String principalFonctionnalite) {
        this.principalFonctionnalite = principalFonctionnalite;
    }
}
