package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeEtablissementDto {
    private Long idModeEtablissement;
    private String libelle;

    public ModeEtablissementDto() {
    }

    public ModeEtablissementDto(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdModeEtablissement() {
        return idModeEtablissement;
    }

    public void setIdModeEtablissement(Long idModeEtablissement) {
        this.idModeEtablissement = idModeEtablissement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
