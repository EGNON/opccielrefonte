package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatutPersonneDto {
    private CleStatutPersonneDto idStatutPersonne;
    private PersonneDto personne;
    private QualiteDto qualite;
    private PersonnelDto personnel;

    public CleStatutPersonneDto getIdStatutPersonne() {
        return idStatutPersonne;
    }

    public void setIdStatutPersonne(CleStatutPersonneDto idStatutPersonne) {
        this.idStatutPersonne = idStatutPersonne;
    }

    public StatutPersonneDto() {
    }

    public StatutPersonneDto(PersonneDto personne, QualiteDto qualite, PersonnelDto personnel) {
        this.personne = personne;
        this.qualite = qualite;
        this.personnel = personnel;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonneDto(PersonneDto personneDto) {
        this.personne = personneDto;
    }

    public QualiteDto getQualite() {
        return qualite;
    }

    public void setQualite(QualiteDto qualiteDto) {
        this.qualite = qualiteDto;
    }

    public PersonnelDto getPersonnel() {
        return personnel;
    }

    public void setPersonnel(PersonnelDto personnelDto) {
        this.personnel = personnelDto;
    }

    @Override
    public String toString() {
        return "StatutPersonneDto{" +
                "idStatutPersonne=" + idStatutPersonne +
                ", personne=" + personne +
                ", qualite=" + qualite +
                ", personnel=" + personnel +
                '}';
    }
}
