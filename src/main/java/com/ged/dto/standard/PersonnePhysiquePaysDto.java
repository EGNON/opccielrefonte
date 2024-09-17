package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.entity.standard.ClePersonnePhysiquePays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonnePhysiquePaysDto {
    private ClePersonnePhysiquePays idPersonnePhysiquePays;
    private PersonnePhysiqueDto personnePhysiqueDto;
    private PaysDto paysDto;

    public PersonnePhysiquePaysDto() {
    }

    public ClePersonnePhysiquePays getIdPersonnePhysiquePays() {
        return idPersonnePhysiquePays;
    }

    public void setIdPersonnePhysiquePays(ClePersonnePhysiquePays idPersonnePhysiquePays) {
        this.idPersonnePhysiquePays = idPersonnePhysiquePays;
    }

    public PersonnePhysiqueDto getPersonnePhysiqueDto() {
        return personnePhysiqueDto;
    }

    public void setPersonnePhysiqueDto(PersonnePhysiqueDto personnePhysiqueDto) {
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
