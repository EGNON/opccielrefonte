package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuartierDto {

    private long idQuartier;
    private String libelleQuartier;
    private ArrondissementDto arrondissementDto;
    private Set<PersonneDto> personneDtos;

    public QuartierDto() {
    }

    public Set<PersonneDto> getPersonneDtos() {
        return personneDtos;
    }

    public void setPersonneDtos(Set<PersonneDto> personneDtos) {
        this.personneDtos = personneDtos;
    }

    public QuartierDto(String libelleQuartier) {
        this.libelleQuartier = libelleQuartier;
    }

    public long getIdQuartier() {
        return idQuartier;
    }

    public void setIdQuartier(long idQuartier) {
        this.idQuartier = idQuartier;
    }

    public String getLibelleQuartier() {
        return libelleQuartier;
    }

    public void setLibelleQuartier(String libelleQuartier) {
        this.libelleQuartier = libelleQuartier;
    }

    public ArrondissementDto getArrondissementDto() {
        return arrondissementDto;
    }

    public void setArrondissementDto(ArrondissementDto arrondissementDto) {
        this.arrondissementDto = arrondissementDto;
    }
}
