package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VilleDto {
    private Long idVille;
    private String libelleVille;
    private CommuneDto commune;

    public VilleDto() {
    }

    public VilleDto(String libelleVille) {
        this.libelleVille = libelleVille;
    }

    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }

    public String getLibelleVille() {
        return libelleVille;
    }

    public void setLibelleVille(String libelleVille) {
        this.libelleVille = libelleVille;
    }

    public CommuneDto getCommune() {
        return commune;
    }

    public void setCommune(CommuneDto commune) {
        this.commune = commune;
    }
}
