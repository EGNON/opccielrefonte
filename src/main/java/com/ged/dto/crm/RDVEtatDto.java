package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RDVEtatDto {
    private Long idRdv;
    private Date dateDebRdv;
    private Date dateFinRdv;
    private String heureFinRdv;
    private Time heureDebutRdv;
    private String objetRdv;
    private String denomination;
    private Date dateDebReelle;
    private Time heureDebReelle;
    private Date dateFinReelle;
    private Time heureFinReelle;
    private String libelleFr;
    private String libelleQuartier;
    private CommuneDto communeDto;
    public RDVEtatDto() {

    }

    public RDVEtatDto(Date dateDebRdv, String objetRdv, PaysDto paysDto, PersonnePhysiqueMoraleDto personneDto, CompteRenduDto compteRenduDto) {
        this.dateDebRdv = dateDebRdv;
        this.objetRdv = objetRdv;
    }

    public Time getHeureDebutRdv() {
        return heureDebutRdv;
    }

    public void setHeureDebutRdv(Time heureDebutRdv) {
        this.heureDebutRdv = heureDebutRdv;
    }

    public CommuneDto getCommuneDto() {
        return communeDto;
    }

    public void setCommuneDto(CommuneDto communeDto) {
        this.communeDto = communeDto;
    }

    public Date getDateDebReelle() {
        return dateDebReelle;
    }

    public void setDateDebReelle(Date dateDebReelle) {
        this.dateDebReelle = dateDebReelle;
    }

    public Time getHeureDebReelle() {
        return heureDebReelle;
    }

    public void setHeureDebReelle(Time heureDebReelle) {
        this.heureDebReelle = heureDebReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public Time getHeureFinReelle() {
        return heureFinReelle;
    }

    public void setHeureFinReelle(Time heureFinReelle) {
        this.heureFinReelle = heureFinReelle;
    }
    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Long getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(Long idRdv) {
        this.idRdv = idRdv;
    }

    public Date getDateDebRdv() {
        return dateDebRdv;
    }

    public void setDateDebRdv(Date dateDebRdv) {
        this.dateDebRdv = dateDebRdv;
    }

    public Date getDateFinRdv() {
        return dateFinRdv;
    }

    public void setDateFinRdv(Date dateFinRdv) {
        this.dateFinRdv = dateFinRdv;
    }

    public String getHeureFinRdv() {
        return heureFinRdv;
    }

    public void setHeureFinRdv(String heureFinRdv) {
        this.heureFinRdv = heureFinRdv;
    }

    public String getObjetRdv() {
        return objetRdv;
    }

    public void setObjetRdv(String objetRdv) {
        this.objetRdv = objetRdv;
    }

    public String getLibelleFr() {
        return libelleFr;
    }

    public void setLibelleFr(String libelleFr) {
        this.libelleFr = libelleFr;
    }

    public String getLibelleQuartier() {
        return libelleQuartier;
    }

    public void setLibelleQuartier(String libelleQuartier) {
        this.libelleQuartier = libelleQuartier;
    }

    @Override
    public String toString() {
        return "RDVDto{" +
                "idRdv=" + idRdv +
                ", dateDebRdv=" + dateDebRdv +
                ", dateFinRdv=" + dateFinRdv +
                ", heureFinRdv=" + heureFinRdv +
                ", objetRdv='" + objetRdv + '\'' +
                ", denomination='" + denomination + '\'' +
                ", dateDebReelle=" + dateDebReelle +
                ", heureDebReelle=" + heureDebReelle +
                ", dateFinReelle=" + dateFinReelle +
                ", heureFinReelle=" + heureFinReelle +
                '}';
    }
}
