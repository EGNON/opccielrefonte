package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.RDVDto;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaysDto {
    private Long idPays;
    private String libelleFr;
    private String libelleEn;
    private int indicatif;
    private boolean estGafi;
    private MonnaieDto monnaie;
    private Set<RDVDto> rdvDtos = new HashSet<>();
    private Set<PersonnePhysiquePaysDto> personnePhysiquePaysDtos;

    public PaysDto() {
    }

    public PaysDto(String libelleFr, String libelleEn, int indicatif, MonnaieDto monnaieDto) {
        this.libelleFr = libelleFr;
        this.libelleEn = libelleEn;
        this.indicatif = indicatif;
        this.monnaie = monnaieDto;
    }

    public Long getIdPays() {
        return idPays;
    }

    public void setIdPays(Long idPays) {
        this.idPays = idPays;
    }

    public String getLibelleFr() {
        return libelleFr;
    }

    public void setLibelleFr(String libelleFr) {
        this.libelleFr = libelleFr;
    }

    public String getLibelleEn() {
        return libelleEn;
    }

    public void setLibelleEn(String libelleEn) {
        this.libelleEn = libelleEn;
    }

    public int getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(int indicatif) {
        this.indicatif = indicatif;
    }

    public boolean isEstGafi() {
        return estGafi;
    }

    public void setEstGafi(boolean estGafi) {
        this.estGafi = estGafi;
    }

    public MonnaieDto getMonnaieDto() {
        return monnaie;
    }

    public void setMonnaieDto(MonnaieDto monnaieDto) {
        this.monnaie = monnaieDto;
    }

    public Set<RDVDto> getRdvDtos() {
        return rdvDtos;
    }

    public void setRdvDtos(Set<RDVDto> rdvDtos) {
        this.rdvDtos = rdvDtos;
    }

    public Set<PersonnePhysiquePaysDto> getPersonnePhysiquePaysDtos() {
        return personnePhysiquePaysDtos;
    }

    public void setPersonnePhysiquePaysDtos(Set<PersonnePhysiquePaysDto> personnePhysiquePaysDtos) {
        this.personnePhysiquePaysDtos = personnePhysiquePaysDtos;
    }
}
