package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TempsDto {

    private long idTemps;

    private String libelle;
    public TempsDto() {
    }

    public TempsDto(String libelle) {
        this.libelle = libelle;
    }

    public long getIdTemps() {
        return idTemps;
    }

    public void setIdTemps(long idTemps) {
        this.idTemps = idTemps;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
