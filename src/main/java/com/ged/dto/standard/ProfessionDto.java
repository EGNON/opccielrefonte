package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessionDto {
    private Long idProf;
    private String libelleProfession;
    private Set<PersonneDto> personneDtos;

    public ProfessionDto() {
    }

    public ProfessionDto(String libelleProfession, String userLogin) {
        this.libelleProfession = libelleProfession;
    }

    public Long getIdProf() {
        return idProf;
    }

    public void setIdProf(Long idProf) {
        this.idProf = idProf;
    }

    public String getLibelleProfession() {
        return libelleProfession;
    }

    public void setLibelleProfession(String libelleProfession) {
        this.libelleProfession = libelleProfession;
    }

    public Set<PersonneDto> getPersonneDtos() {
        return personneDtos;
    }

    public void setPersonneDtos(Set<PersonneDto> personneDtos) {
        this.personneDtos = personneDtos;
    }
}
