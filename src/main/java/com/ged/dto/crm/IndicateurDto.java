package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndicateurDto {
    private Long idIndicateur;
    private String libelle;
    private String code;
    private Set<DetailObjectifDto> detailObjectifDtos = new HashSet<>();
    private Set<ObjectifReelDto> objectifReelDtos;
    public IndicateurDto() {
    }

    public IndicateurDto(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdIndicateur() {
        return idIndicateur;
    }

    public void setIdIndicateur(Long idIndicateur) {
        this.idIndicateur = idIndicateur;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<ObjectifReelDto> getObjectifReelDtos() {
        return objectifReelDtos;
    }

    public void setObjectifReelDtos(Set<ObjectifReelDto> objectifReelDtos) {
        this.objectifReelDtos = objectifReelDtos;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<DetailObjectifDto> getDetailObjectifDtos() {
        return detailObjectifDtos;
    }

    public void setDetailObjectifDtos(Set<DetailObjectifDto> detailObjectifs) {
        this.detailObjectifDtos = detailObjectifs;
    }

    @Override
    public String toString() {
        return "Indicateur [" +
                "idIndicateur=" + idIndicateur +
                ", libelle='" + libelle + '\'' +
                ']';
    }
}
