package com.ged.dto.etats;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PointRachatGlobalDto {

    private BigDecimal nombrePartSousRachat;
    private BigDecimal montantSousALiquider;
    private BigDecimal commisiionSousRachat;
    private BigDecimal retrocessionSousRachat;
    private Long idPersonne;
    private String raisonSociale;
    private String libelleTypePersonne;
    private String denominationOpcvm;
    private Long idOpcvm;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public BigDecimal getNombrePartSousRachat() {
        return nombrePartSousRachat;
    }

    public void setNombrePartSousRachat(BigDecimal nombrePartSousRachat) {
        this.nombrePartSousRachat = nombrePartSousRachat;
    }

    public BigDecimal getMontantSousALiquider() {
        return montantSousALiquider;
    }

    public void setMontantSousALiquider(BigDecimal montantSousALiquider) {
        this.montantSousALiquider = montantSousALiquider;
    }

    public BigDecimal getCommisiionSousRachat() {
        return commisiionSousRachat;
    }

    public void setCommisiionSousRachat(BigDecimal commisiionSousRachat) {
        this.commisiionSousRachat = commisiionSousRachat;
    }

    public BigDecimal getRetrocessionSousRachat() {
        return retrocessionSousRachat;
    }

    public void setRetrocessionSousRachat(BigDecimal retrocessionSousRachat) {
        this.retrocessionSousRachat = retrocessionSousRachat;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getLibelleTypePersonne() {
        return libelleTypePersonne;
    }

    public void setLibelleTypePersonne(String libelleTypePersonne) {
        this.libelleTypePersonne = libelleTypePersonne;
    }

    public String getDenominationOpcvm() {
        return denominationOpcvm;
    }

    public void setDenominationOpcvm(String denominationOpcvm) {
        this.denominationOpcvm = denominationOpcvm;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
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
}
