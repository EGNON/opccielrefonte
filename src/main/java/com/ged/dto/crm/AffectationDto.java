package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonnelDto;

import java.time.LocalDateTime;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AffectationDto {
    private Long idAffectation;
    private LocalDateTime dateAffectation;
    private LocalDateTime dateSoumission;
    private PersonnelDto personnel;
//    @JsonManagedReference
    private Set<ObjectifAffecteDto> objectifAffectes;

    public Long getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Long idAffectation) {
        this.idAffectation = idAffectation;
    }

    public LocalDateTime getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(LocalDateTime dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public PersonnelDto getPersonnel() {
        return personnel;
    }

    public void setPersonnel(PersonnelDto personnel) {
        this.personnel = personnel;
    }

    public Set<ObjectifAffecteDto> getObjectifAffectes() {
        return objectifAffectes;
    }

    public void setObjectifAffectes(Set<ObjectifAffecteDto> objectifAffectes) {
        this.objectifAffectes = objectifAffectes;
    }

    public LocalDateTime getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(LocalDateTime dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    @Override
    public String toString() {
        return "AffectationDto{" +
                "idAffectation=" + idAffectation +
                ", dateAffectation=" + dateAffectation +
                ", personnel=" + personnel +
//                ", objectifReels=" + objectifReels +
//                ", objectifAffectes=" + objectifAffectes +
                '}';
    }
}
