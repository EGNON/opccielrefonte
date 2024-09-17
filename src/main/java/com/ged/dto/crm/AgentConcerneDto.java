package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonnelDto;

import java.sql.Time;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentConcerneDto {
    private PersonnelDto personnelDto;
    private RDVDto rdvDto;
    private boolean etat;

    public AgentConcerneDto() {
    }

    public AgentConcerneDto(PersonnelDto personnelDto, RDVDto rdvDto, Date dateDebReelle, Time heureDebReelle) {
        this.personnelDto = personnelDto;
        this.rdvDto = rdvDto;
    }


    public PersonnelDto getPersonnelDto() {
        return personnelDto;
    }

    public void setPersonnelDto(PersonnelDto personnelDto) {
        this.personnelDto = personnelDto;
    }

    public RDVDto getRdvDto() {
        return rdvDto;
    }

    public void setRdvDto(RDVDto rdvDto) {
        this.rdvDto = rdvDto;
    }


    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
