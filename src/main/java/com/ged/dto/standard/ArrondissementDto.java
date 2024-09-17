package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrondissementDto {
    private Long idArrondissement;
    private String libelleArrondissement;
    private CommuneDto commune;

    public Long getIdArrondissement() {
        return idArrondissement;
    }

    public void setIdArrondissement(Long idArrondissement) {
        this.idArrondissement = idArrondissement;
    }

    public String getLibelleArrondissement() {
        return libelleArrondissement;
    }

    public void setLibelleArrondissement(String libelleArrondissement) {
        this.libelleArrondissement = libelleArrondissement;
    }

    public CommuneDto getCommune() {
        return commune;
    }

    public void setCommune(CommuneDto commune) {
        this.commune = commune;
    }
}
