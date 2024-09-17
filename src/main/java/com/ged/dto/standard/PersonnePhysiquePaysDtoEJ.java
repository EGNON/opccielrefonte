package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.entity.standard.ClePersonnePhysiquePays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonnePhysiquePaysDtoEJ {
    private ClePersonnePhysiquePays idPersonnePhysiquePays;
    private PersonnePhysiqueDtoEJ personnePhysiqueDto;
    private PaysDto paysDto;

    public PersonnePhysiquePaysDtoEJ() {
    }

    public ClePersonnePhysiquePays getIdPersonnePhysiquePays() {
        return idPersonnePhysiquePays;
    }

    public void setIdPersonnePhysiquePays(ClePersonnePhysiquePays idPersonnePhysiquePays) {
        this.idPersonnePhysiquePays = idPersonnePhysiquePays;
    }

    public PersonnePhysiqueDtoEJ getPersonnePhysiqueDto() {
        return personnePhysiqueDto;
    }

    public void setPersonnePhysiqueDto(PersonnePhysiqueDtoEJ personnePhysiqueDto) {
        this.personnePhysiqueDto = personnePhysiqueDto;
    }

    public PaysDto getPaysDto() {
        return paysDto;
    }

    public void setPaysDto(PaysDto paysDto) {
        this.paysDto = paysDto;
    }

    @Override
    public String toString() {
        return "PersonnePhysiquePaysDto{" +
                "personnePhysiqueDto=" + personnePhysiqueDto +
                ", paysDto=" + paysDto +
                '}';
    }
}
