package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.DetailObjectifDto;
import com.ged.dto.crm.ObjectifReelDto;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeriodiciteDto {
    private Long idPeriodicite;
    private String libelle;
    private Set<DetailObjectifDto> detailObjectifDtos = new HashSet<>();
    private Set<ObjectifReelDto> objectifReelDtos;
    public PeriodiciteDto() {
    }

    public PeriodiciteDto(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdPeriodicite() {
        return idPeriodicite;
    }

    public void setIdPeriodicite(Long idPeriodicite) {
        this.idPeriodicite = idPeriodicite;
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

    public void setDetailObjectifDtos(Set<DetailObjectifDto> detailObjectifDtos) {
        this.detailObjectifDtos = detailObjectifDtos;
    }

    public Set<ObjectifReelDto> getObjectifReelDtos() {
        return objectifReelDtos;
    }

    public void setObjectifReelDtos(Set<ObjectifReelDto> objectifReelDtos) {
        this.objectifReelDtos = objectifReelDtos;
    }

    @Override
    public String toString() {
        return "Periodicite [" +
                "idPeriodicite=" + idPeriodicite +
                ", libelle='" + libelle + '\'' +
                ']';
    }
}
