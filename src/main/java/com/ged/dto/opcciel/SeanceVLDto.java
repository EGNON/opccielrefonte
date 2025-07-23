package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeanceVLDto {
	private String libelle;

    private Boolean etat;

    private Long numSeance;
    private Long numero;
    private BigDecimal vl;
    private LocalDateTime dateOuverture;
    private LocalDateTime dateFermeture;
    public SeanceVLDto() {
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getNumSeance() {
        return numSeance;
    }

    public void setNumSeance(Long numSeance) {
        this.numSeance = numSeance;
    }

    public BigDecimal getVl() {
        return vl;
    }

    public void setVl(BigDecimal vl) {
        this.vl = vl;
    }

    public LocalDateTime getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(LocalDateTime dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public LocalDateTime getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(LocalDateTime dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
